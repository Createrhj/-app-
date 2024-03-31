<template>
	<view class="payMain">
	<scroll-view class="content" scroll-y>	
	
		<view class="address">
			<view class="mainAddress" @click="toAddressChange()">
				<uni-icons type="location" style="display: inline;color: #ba5140; font-size: 25px;"></uni-icons>
			{{userAddress.detailAddress}}</view>
			<text style="margin-left: 45px;font-size: 16px;">{{userAddress.name}} {{userAddress.phone}}</text>
		</view>
		
		<view class="goods" v-for="goods in goods" :key="goods.goodsName">
			<text class="goodsName">{{goods.goodsStore}}</text>
			<view class="goodsInfor">
				<image :src="imgPrefix+goods.goodsImg" mode="aspectFill"></image>
				<view class="inforMid">
					<text style="font-weight: bold;">{{goods.goodsName}}</text>
					<text>发货地: {{goods.goodsLocation}}</text>
					<uni-tag 
					style="background-color: #cb523e; border-color: #cb523e; color: #fff;width: 60px;"
					text="双十一优惠" type="primary"/>
				</view>
				<view class="inforRight">
					<text>￥{{goods.goodsTotal}}</text>
					<text>×{{goods.goodsNumber}}</text>
				</view>
			</view>
		</view>
		
	</scroll-view>
		<view class="pay">
			<view class="price">
				<text>共{{goods.length}}件</text>
				<text style="font-weight: bold;margin-left: 7px;">合计:</text>
				<text style="font-weight: bold;font-size: 20px;">￥{{orders.ordersMoney}}</text>
			</view>
			<button class="btn" @click="addOrder()">支付订单</button>
		</view>
	</view>
</template>

<script>
	import MarketApi from '../../api/MarketApi.js'
	export default {
		data() {
			return {
				CurUser:[],
				goods:[],
				userAddress:[],
				imgPrefix:'http://localhost:9527/app/goodsImg/',
				orders:{
					addressID: 1,
					ordersDate: "",
					ordersID: "",
					ordersMoney: 0,
					ordersStatue: "待发货",
					userID: '',
				}
			}
		},
		methods: {
			buyGoods(){
				MarketApi.buyGoods(this.CurUser.userID).then(res=>res.data).then(res=>{
					for(const key in res.data){
						const imgList=res.data[key].goodsImg.split('/')
						res.data[key].goodsImg=imgList[0]
						this.orders.ordersMoney=this.orders.ordersMoney+res.data[key].goodsTotal
					}
					this.goods=res.data
				})
			},
			addOrder(){
				MarketApi.addOrders(this.orders).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `支付成功`,
						icon: 'none'
					})
				})
				uni.switchTab({
					url:'/pages/index/index'
				})
			},
			toAddressChange(){
				uni.navigateTo({
					url:'/pages/addressChange/addressChange'
				})
			}
		},
		mounted(){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			this.orders.userID=this.CurUser.userID
			this.buyGoods()
		},
		onLoad(e){
			MarketApi.getAddress(e.addressID).then(res=>res.data).then(res=>{
				this.userAddress=res.data
			})
		}
	}
</script>

<style lang="scss">
.content {
	flex: 1;
	overflow: auto;
}	
.payMain{ 
	display: flex;
	flex-direction: column;
	height: 94vh;
}
.address{
	width: 700rpx;
	height: 150rpx;
	background-color: #f5f2e9;
	border: 1px solid #d4d3ca;
	margin: 10px;
	border-radius: 10px;
	padding-top: 15px;
	.mainAddress{
		font-weight: bold;
		font-size: 20px;
		margin-left: 20px;
		margin-bottom: 10px;
	}
}
.goods{
	width: 700rpx;
	height: 320rpx;
	background-color: aquamarine;
	margin-left: 10px;
	background-color: #f5f2e9;
	border: 1px solid #d4d3ca;
	margin-bottom: 10px;
	border-radius: 10px;
	padding-top: 15px;
	display: flex;
	flex-direction: column;
	.goodsName{
		display: flex;
		font-size: 18px;
		font-weight: bold;
		margin-left: 15px;
	}
	.goodsInfor{
		flex-direction: row;
		display: flex;
		margin-left: 15px;
		margin-top: 10px;
		.inforMid{
			display: flex;
			flex-direction: column;
			margin-left: 12px;
			width: 300rpx;
			text{
				margin-top: 5px;
			}
			.uni-tag{
				margin-top: 15px;
			}
		}
		.inforRight{
			width: 100rpx;
			height: 100rpx;
			margin-left: 10px;
			text-align: right;
			text{
				font-weight: bold;
				display: block;
				color: #b93a26;
				margin-bottom: 15px;
			}
		}
		image{
			width: 200rpx;
			height: 200rpx;
			border-radius: 10px;
		}
	}
}
.pay{
	width: 100%;
	height: 120rpx;
	background-color: #95302e;;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	text{
		color: #FFFFFF;
	}
	.price{
		margin-top: 15px;
		margin-left: 190rpx;
	}
	.btn{
		width: 200rpx;
		height: 80rpx;
		font-size: 15px;
		border-radius: 20px;
		margin-top: 10px;
		margin-right: 10px;
	}
}
</style>
