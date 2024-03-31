<template>
	<view class="order_home">
		<view class="tops">
		
			<view class="item" @click='updateShow(0)'>
				<view v-if="showStatus===0">
					<image src="../../static/hj/订单 (1).png" class="imagetop"></image>
					<view>全部</view>
				</view>
				<view v-else >
					<image src="../../static/hj/订单.png" class="imagetop"></image>
					<view>全部</view>
				</view>
			</view>
			<view class="item" @click='updateShow(1)'>			
				<view v-if="showStatus===1">
					<image src="../../static/hj/待付款订单active.png" class="imagetop"></image>
					<view>已完成</view>
				</view>
				<view v-else >
					<image src="../../static/hj/待付款.png"class="imagetop"></image>
					<view>已完成</view>
				</view>
			</view>
			<view class="item" @click='updateShow(2)'>			
				<view v-if="showStatus===2">
					<image src="../../static/hj/待发货 (1).png" class="imagetop"></image>
					<view>待发货</view>
				</view>
				<view v-else >
					<image src="../../static/hj/待发货.png" class="imagetop"></image>
					<view>待发货</view>
				</view>
			</view>
			<view class="item" @click='updateShow(3)'>			
				<view v-if="showStatus===3">
					<image src="../../static/hj/待收货 (1).png" class="imagetop"></image>
					<view>待收货</view>
				</view>
				<view v-else >
					<image src="../../static/hj/待收货.png" class="imagetop"></image>
					<view>待收货</view>
				</view>
			</view>
			<view class="item" @click='updateShow(4)'>			
				<view v-if="showStatus===4">
					<image src="../../static/hj/消息.png"class="imagetop"></image>
					<view>待评价</view>
				</view>
				<view v-else >
					<image src="../../static/hj/chat-white.png" class="imagetop"></image>
					<view>待评价</view>
				</view>
			</view>
		</view>
		
		<!-- 订单展示 -->
		<view class="order" v-for="order in orders" :key="order.orderID" >
		<!-- 		<uni-card :body-style="{ padding: '10px' }" style="margin-left: 10px;margin-right: 10px"> -->
			<uni-card
			style="	background-color: #f5f2e9;border: 1px solid #d4d3ca;border-radius: 15px" >
			<!-- 顶部商店展示 -->
				<template v-slot:title>
					<view class="card-top" style="height: 20px;">
						<text 
						style="margin-top: 10px;margin-left: 10px;font-weight: bold;font-size: 17px;">
						{{order.goodsStore}}</text> 
						<view class="status" style="margin-top: 10px;margin-right: 10px;color: #683632;font-weight: bold;">{{order.ordersStatue}}</view>
					</view>
				</template>
				<!-- 中间商品详情展示 -->
				<template>
					<view style="width: 100%;height: 145px;text-align: left;">
						<image :src="imgPrefix+order.goodsImg"
						mode="aspectFill"
						 style="width: 120px;height: 120px;float: left;margin: 5px 10px 5px 5px"
						 />
						<view style="margin:10px 0 8px 0">
							<text style="font-weight: bold;font-size: 15px;">{{order.goodsName}}</text>
							<text style="float: right;margin-right: 15px;font-size: 15px;">×{{order.goodsNumber}}</text>
						</view>
						<view style="margin-bottom: 5px;">
							<uni-tag
							 style="background-color: #cb523e; border-color: #cb523e; color: #fff;width: 60px;"
							 text="双十一优惠" type="primary"/>
						</view>
						<view>
							<text style="color:#999;font-size: 13px;">
							发货地: {{order.goodsLocation}}
							</text>
						</view>
						<view>
							<text style="color:#999;font-size: 13px;">
							运费: 包邮
							</text>
						</view>
						<view >
							<text style="font-weight: bold;color: #b93a26;font-size: 20px;" >￥{{ order.goodsTotal }}</text>
						</view>

					</view>
				</template>
				
				  </uni-card>
				<!-- </uni-card> -->
			</view>
	</view>
</template>

<script>
import MarkApi from '../../api/MarketApi.js'
	export default{
		data(){
			return{
				showStatus:0  ,//订单状态
				CurUser:[],
				orders:[],
				imgPrefix:'http://localhost:9527/app/goodsImg/'
			}
		},
		methods:{
			updateShow(num){
				this.showStatus=num
				this.userOrders()
			},
			ordersDetail(ordersID){
				MarkApi.selectOrders(ordersID).then(res=>res.data).then(res=>{
					for(const key in res.data){
						const imgList=res.data[key].goodsImg.split('/')
						res.data[key].goodsImg=imgList[0]
						this.orders.push(res.data[key])
					}
				})
			},
			userOrders(){
				this.orders=[]
				MarkApi.userOrders(this.CurUser.userID).then(res=>res.data).then(res=>{
					if(this.showStatus==0){
						for(const key in res.data){
							this.ordersDetail(res.data[key].ordersID)
						}
					}else if(this.showStatus==1){
						for(const key in res.data){
							if(res.data[key].ordersStatue=="已完成"){
								this.ordersDetail(res.data[key].ordersID)
							}
						}
					}
					else if(this.showStatus==2){
						for(const key in res.data){
							if(res.data[key].ordersStatue=="待发货"){
								this.ordersDetail(res.data[key].ordersID)
							}
						}
					}
					else if(this.showStatus==3){
						for(const key in res.data){
							if(res.data[key].ordersStatue=="待收货"){
								this.ordersDetail(res.data[key].ordersID)
							}
						}
					}
					else if(this.showStatus==4){
						for(const key in res.data){
							if(res.data[key].ordersStatue=="待评价"){
								this.ordersDetail(res.data[key].ordersID)
							}
						}
					}
				})
			},
			getUser(){
				this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			}
		},
		computed:{
			//返回订单状态
			showOrderStatusList(){
				if (this.showStatus === 1){
					//返回待支付的订单
					return this.goodsDetail.filter(item=>{
						return item.status === 1
					})
				}else if(this.showStatus === 2){
					//返回待发货的订单
					return this.goodsDetail.filter(item=>{
						return item.status === 2
					})
				}else if(this.showStatus === 3){
					//返回待收货的订单
					return this.goodsDetail.filter(item=>{
						return item.status === 3
					})
				}else if(this.showStatus === 4){
					//返回待评价的订单
					return this.goodsDetail.filter(item=>{
						return item.status === 4
					})
				}else{
					//返回首页订单
					return this.goodsDetail
				}
			}
		},
		mounted(){
			this.getUser()
			this.userOrders()
		},
	}
</script>

<style lang="scss">
	.order_home{
		//padding: 10rpx;
		.tops{
			padding: 10rpx;
			height: 100rpx;
			//background-color: #fff;
			display: flex;
			.item{
				font-size: 25rpx;
				flex: 1;
				text-align: center;
				.imagetop{
					width: 45rpx;
					height: 45rpx;
					border-radius: 15rpx;
				}
			}
		}
		.order{
			// background-color: #fff;
			// margin: 4rpx 20rpx;
			// border-radius: 20px;
			.card-top{  //水平布局订单上部分布局
				display: flex;
				flex-flow: row nowrap;
				justify-content: space-between;//里面字体间距
			}
			
		
		}
	}
</style>