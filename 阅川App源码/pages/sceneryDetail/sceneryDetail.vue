<template>
	<view class="sceneryDetail">
		<view class="sceneryImg">
			<swiper class="swiper"
			:autoplay="true" 
			:interval="5000" 
			:duration="800"
			:circular="true">
				<swiper-item v-for="img in imgList" :key="img">
					<image  :src="imgPrefix+img"></image>
				</swiper-item>
			</swiper>
		</view>
		
		<view class="sceneryInfor">
			<view class="inforLine">
				<text style="font-size: 20px;font-weight: bold;">{{scenery.sceneryName}}</text>
			</view>
			<view class="inforLine">
				<uni-rate :touchable="false" :value="4"/>
				<text style="font-weight: bold;font-size: 20px;margin-left: 10px;">{{scenery.sceneryCount}}</text>
				<text>8.6万人做出评价</text>
			</view>
			<view class="inforLine">
				<uni-tag 
				style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
				class="tag" :text="scenery.sceneryEvaluate" type="primary"></uni-tag>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">景点类型:</text>
				<text>{{scenery.sceneryCategory}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">所在城市:</text>
				<text>{{scenery.sceneryCity}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">具体位置:</text>
				<text>{{scenery.sceneryAddress}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">门票价格:</text>
				<text>{{scenery.sceneryPrice}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">景点介绍:</text>
			</view>
			<text style="margin-left: 10px;margin-bottom: 20px;margin-top: 5px;	padding-right: 10px;">{{scenery.sceneryDetail}}</text>
		</view>
	</view>
</template>
<script>
	import TravelApi from '../../api/TravelApi.js'
export default {
	data() {
		return {
			scenery:[],
			imgList:[],
			imgPrefix:'http://localhost:9527/app/sceneryImg/',
		}
	},
	methods: {
		getImgList(){
			this.imgList=this.scenery.sceneryImg.split('/')
			this.imgList.pop()
		}
		},
		onLoad(e){
			TravelApi.sceneryDetail(e.sceneryID).then(res=>res.data).then(res=>{
				this.scenery=res.data[0]
				this.getImgList()
			})
		}
	}
</script>


<style lang="scss">
.sceneryImg{
	width: 100%;
	height: 500rpx;
	background-color: #FFBB57;
	position: absolute;
	.swiper{
		width: 100%;
		height: 500rpx;
		image{
			width: 100%;
			height: 480rpx;
		}
	}
}
.sceneryInfor{
	width: 100%;
	background-color: antiquewhite;
	position: absolute;
	top: 450rpx;
	border-top-left-radius:15px ;
	display: flex;
	flex-direction: column;
	map{
		width: 700rpx;
		margin-top: 10px;
		margin-left: 10px;
		border-radius: 10px;
		height: 280rpx;
	}
	.inforLine{
		display: flex;
		flex-direction: row;
		margin-left: 10px;
		margin-top: 10px;

		text{
			margin-right: 10px;
		}
		.tag{
			margin-right: 10px;
			border-radius: 15px;
			font-size: 13px;
		}
	}
}
</style>
