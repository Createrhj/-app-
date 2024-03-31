<template>
	<view>
		<uni-search-bar 
		radius="100" 
		placeholder="请输入搜索内容" 
		clearButton="none" 
		cancelButton="none" 
		@confirm="search" />
		
		<view class="goodsView">
			<view class="topTitle">
				<text class="title">服饰</text>
				<button @click="more">更多</button>
			</view>
			<view class="goodsLine">
				<view class="goodsInformation" @click="toGoodsDetail(goods.goodsID)" v-for="goods in cloth" :key="goods.goodsID">
					<image :src="imgPrefix+goods.goodsImg" class="imageView" mode="aspectFill"></image>
					<text style="margin-left: 5px;">{{goods.goodsName}}</text>
					<text class="price">￥{{goods.goodsPrice}}</text>
				</view>
			</view>
		</view>
		
		<view class="goodsView">
			<view class="topTitle">
				<text class="title">饮食</text>
				<button @click="more">更多</button>
			</view>
			<view class="goodsLine">
				<view class="goodsInformation" @click="toGoodsDetail(goods.goodsID)" v-for="goods in foods" :key="goods.goodsID">
					<image :src="imgPrefix+goods.goodsImg" class="imageView" mode="aspectFill"></image>
					<text style="margin-left: 5px;">{{goods.goodsName}}</text>
					<text class="price">￥{{goods.goodsPrice}}</text>
				</view>
			</view>
		</view>
		
		<view class="goodsView">
			<view class="topTitle">
				<text class="title">文具</text>
				<button @click="more">更多</button>
			</view>
			<view class="goodsLine">
				<view class="goodsInformation" @click="toGoodsDetail(goods.goodsID)" v-for="goods in stationery" :key="goods.goodsName">
					<image :src="imgPrefix+goods.goodsImg" class="imageView" mode="aspectFill"></image>
					<text style="margin-left: 5px;">{{goods.goodsName}}</text>
					<text class="price">￥{{goods.goodsPrice}}</text>
				</view>
			</view>
		</view>
		
		<view class="goodsView">
			<view class="topTitle">
				<text class="title">娱乐</text>
				<button @click="more">更多</button>
			</view>
			
			<view class="goodsLine">
				<view class="goodsInformation" @click="toGoodsDetail(goods.goodsID)" v-for="goods in entertainment" :key="goods.goodsID">
					<image :src="imgPrefix+goods.goodsImg" class="imageView" mode="aspectFill"></image>
					<text style="margin-left: 5px;">{{goods.goodsName}}</text>
					<text class="price">￥{{goods.goodsPrice}}</text>
				</view>
			</view>
		</view>
		
		<view class="goodsView">
			<view class="topTitle">
				<text class="title">茶类</text>
				<button @click="more">更多</button>
			</view>
			<view class="goodsLine">
				<view class="goodsInformation" @click="toGoodsDetail(goods.goodsID)" v-for="goods in tea" :key="goods.goodsName">
					<image :src="imgPrefix+goods.goodsImg" class="imageView" mode="aspectFill"></image>
					<text style="margin-left: 10px;">{{goods.goodsName}}</text>
					<text class="price">￥{{goods.goodsPrice}}</text>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	import MarketApi from '../../api/MarketApi.js'
	export default {
		data() {
			return {
				cloth:[],
				foods:[],
				stationery:[],
				entertainment:[],
				tea:[],
				searchMap:{
					goodsName:'',
					goodsCategory:'',
					goodsLocation:'',
					lowPrice:'',
					highPrice:'',
				},
				imgPrefix:'http://localhost:9527/app/goodsImg/'
			}
		},
		methods: {
			allGoods(){
				MarketApi.allGoods(this.searchMap).then(res=>res.data).then(res=>{
					for (const key in res.data){
						const imgList=res.data[key].goodsImg.split('/')
						res.data[key].goodsImg=imgList[0]
					}
					for (const key in res.data){
						if (res.data[key].goodsCategory=="服饰"&&this.cloth.length!=2){
							this.cloth.push(res.data[key])
						}else if(res.data[key].goodsCategory=="饮食"&&this.foods.length!=2){
							this.foods.push(res.data[key])
						}else if(res.data[key].goodsCategory=="文具"&&this.stationery.length!=2){
							this.stationery.push(res.data[key])
						}else if(res.data[key].goodsCategory=="娱乐"&&this.entertainment.length!=2){
							this.entertainment.push(res.data[key])
						}else if(res.data[key].goodsCategory=="茶类"&&this.tea.length!=2){
							this.tea.push(res.data[key])
						}
					}
					console.log(this.cloth)
				})
			},
			more(){
				uni.navigateTo({
					url: '/pages/goodsSearch/goodsSearch'
				})
			},
			toGoodsDetail(goodsID){
				uni.navigateTo({
					url: '/pages/goodsDetail/goodsDetail?goodsID='+goodsID
				})
			}
		},
		mounted(){
			this.allGoods()
		}
	}
</script>

<style lang="scss">
text{
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}
button{
	width: 60px;
	height: 30px;
	float: right;
	font-size: 5px;
	border-radius: 50px;
	margin-bottom: 10px;
	outline: none;
	border: none;
	margin-right: 20px;
	color: #EAEEF1;
	background-color: #CB523E;
}

.title{
	font-size: 20px;
	margin-left: 15px;
	float: left;
	color: #631216;
}
.goodsView{
	display: flex;
	flex-direction: column;
	.goodsLine{
		display: flex;
		flex-direction: row;
		.goodsInformation{
			width: 320rpx;
			height: 400rpx;
			margin-left: 15px;
			margin-right: 10px;
			margin-bottom: 20px;
			background-color: #f5f2e9;
			border-radius: 15px;
			float: left;
			clear: right;
			.imageView{
				width: 320rpx;
				height: 280rpx;
				border-top-right-radius: 15px;
				border-top-left-radius: 15px;
			}
			.price{
				margin-left: 5px;
				color: #b93a26;
			}
		}
	}
}

</style>
