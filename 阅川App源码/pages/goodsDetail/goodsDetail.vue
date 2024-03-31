<template>
	<view class="goodsDetail">
		<scroll-view class="content" scroll-y>
		<view class="detailTop">
			<swiper class="swiper"
			:autoplay="true" 
			:interval="5000" 
			:duration="800"
			:circular="true">
				<swiper-item v-for="img in imgList" :key="img">
					<image :src="imgPrefix+img" mode="aspectFill"></image>
				</swiper-item>
			</swiper>
			<text class="price">￥78.9</text>
			<text class="goodsName">{{goods.goodsName}}</text>
		</view>
		<view class="goodsParameter">
			<view class="parameter">
				<text style="color: #683632;font-weight: bold;font-size: 16px;">店铺</text>
				<text>{{goods.goodsStore}}</text>
			</view>
			<view class="parameter">
				<text style="color: #683632;font-weight: bold;font-size: 16px;">货源</text>
				<text>{{goods.goodsLocation}}</text>
			</view>
			<view class="parameter">
				<text style="color: #683632;font-weight: bold;font-size: 16px;">活动</text>
				<uni-tag text="双十一"
					:inverted="true"
					type="warning"
					size="mini"/>
			</view>
			<view class="parameter">
				<text style="color: #683632;font-weight: bold;font-size: 16px;">介绍</text>
				<uni-tag 
				v-for="tag in tagDetail" :key="tag"
				:text="tag"
				style="background-color: #cb523e; border-color: #cb523e; color: #fff;margin-right: 5px;" 
				size="mini"/>
			</view>

		</view>
		<view class="goodsRecommend">
			<text style="color: #683632;font-weight: bold;font-size: 17px;">相关推荐</text>
			<view class="recommend" v-for="recommend in recommendList" :key="recommend.goodsName"
			 @click="toRecommendDetail(recommend.goodsID)">
				<image :src="imgPrefix+recommend.goodsImg" mode="aspectFill"></image>
				<text>{{recommend.goodsName}}</text>
			</view>
		</view>
		</scroll-view>
		
		<view class="goods-carts">
			<uni-goods-nav 
			:options="options" 
			:fill="true" 
			:button-group="buttonGroup" 
			@click="onClick"
			@buttonClick="buttonClick" />
		</view>
	</view>
</template>

<script>
	import MarkApi from '../../api/MarketApi.js'
	export default {
		data() {
			return {
				goods:"",
				imgPrefix:'http://localhost:9527/app/goodsImg/',
				imgList:'',
				recommendList:[],
				CurUser:'',
				cart:[],
				tagDetail:'',
				searchMap:{
					goodsName:'',
					goodsCategory:'',
					goodsLocation:'',
					lowPrice:'',
					highPrice:''
				},
				options: [{
					icon: 'shop',
					text: '店铺',
					info: 2,
				}, {
					icon: 'cart',
					text: '购物车',
					info: '',
				}],
				buttonGroup: [{
						text: '加入购物车',
						backgroundColor: '#cb523e',
						color: '#fff'
					},
					{
						text: '立即购买',
						backgroundColor: '#b93a26',
						color: '#fff'
					}
				],
		
			}
		},
		methods: {
			getCatNumber(){
				if(this.CurUser!=null){
					MarkApi.selectCart(this.CurUser.userID).then(res=>res.data).then(res=>{
						if(res.code==200){
							this.cart=res.data
							this.options[1].info=res.data.length
						}
					})
				}
			},
			buttonClick(e) {
				if(this.CurUser==null){
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}else{
					MarkApi.addCart(this.CurUser.userID,this.goods.goodsID,1).then(res=>res.data).then(res=>{
						if(res.code==200){
							uni.showToast({
								title: `加入购物车成功`,
								icon: 'none'
							})
						}
					})
				}
			},
			onClick(){
				if(this.CurUser==null){
					uni.showToast({
						title: `用户请先登录`,
						icon: 'none'
					})
				}else{
					uni.navigateTo({
						url:'/pages/shoppingCart/shoppingCart'
					})
				}
			},
			getRecommend(){
				MarkApi.allGoods(this.searchMap).then(res=>res.data).then(res=>{
					for(const key in res.data){
						if (res.data[key].goodsName!=this.goods.goodsName){
							const imgList=res.data[key].goodsImg.split('/')
							res.data[key].goodsImg=imgList[0]
							this.recommendList.push(res.data[key])
						}
						if(this.recommendList.length==3) break
					}
				})
			},
			toRecommendDetail(goodsID){
					uni.navigateTo({
						url: '/pages/goodsDetail/goodsDetail?goodsID='+goodsID
					})
			}
		},
		onLoad(e){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			MarkApi.goodsDetail(e.goodsID).then(res=>res.data).then(res=>{
				this.goods=res.data[0]
				this.tagDetail=this.goods.goodsDetail.split('、')
				this.imgList=res.data[0].goodsImg.split('/')
				this.imgList.pop()
				this.searchMap.goodsCategory=this.goods.goodsCategory
				this.getRecommend()
				this.getCatNumber()
			})
			
		},
	}
</script>


<style lang="scss" scoped>
	
/deep/ .uni-tab__dot[data-v-279c619b]{
	background-color: #b93a26 !important;
}
.goodsDetail{
	display: flex;
    flex-direction: column;
    height: 100vh;
}
.content {
	flex: 1;
	overflow: auto;
}
.goods-carts{
	position: sticky;
	bottom: 0;
	left: 0;
	right: 0;
}
.detailTop{
	width: 100%;
	height: 740rpx;
	background-color: #f5f2e9;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	.swiper{
		width: 100%;
		height: 600rpx;
		background-color: #f5f2e9;
		swiper-item{
			width: 100%;
			height: 600rpx;
			image{
				width: 100%;
				height: 600rpx;
			}
		}
	}
	.price{
		font-size: 20px;
		color: #b93a26;
		font-weight: bold;
		display: block;
		margin-top: 10px;
		margin-left: 10px;
		margin-bottom: 8px;
	}
	.goodsName{
		font-size: 20px;
		margin-left: 10px;		
		font-weight: bold;
	}
}
.goodsParameter{
	width: 100%;
	height: 200rpx;
	border-radius: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	background-color: #f5f2e9;
	.parameter{
		width: 100%;
		height: 50rpx;
		margin-left: 10px;
		text{

			margin-right: 10px;
		}
	}
	
}
.goodsRecommend{
	width: 100%;
	height: 280rpx;
	background-color: #f5f2e9;
	margin-top: 15px;
	border-radius: 10px;
	padding-top: 10px;
	padding-bottom: 60px;
	text{
		margin-left: 10px;
		font-size: 15px;
		font-weight: bold;
		display: block;
	}
	.recommend{
		width: 30%;
		margin-top: 5px;
		margin-left: 10px;
		height: 250rpx;
		background-color: #FFFFFF;
		overflow:hidden;
		float: left;
		image{
			width: 100%;
			height: 190rpx;
		}
		text{
			font-size: 5px;
			height: 20px;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
	}
}
</style>
