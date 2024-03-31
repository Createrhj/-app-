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
				<text style="font-size: 20px;font-weight: bold;">{{museum.museumName}}</text>
			</view>
			<view class="inforLine">
				<uni-rate :touchable="false" :value="4"/>
				<text style="font-weight: bold;font-size: 20px;margin-left: 10px;">4.5</text>
				<text>8.6万人做出评价</text>
			</view>
			<view class="inforLine">
				<uni-tag 
				style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
				class="tag" :text="evaluation" type="primary" v-for="evaluation in tagEvaluate"></uni-tag>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">博物馆类型:</text>
				<text>{{museum.museumCategory}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">所在城市:</text>
				<text>{{museum.museumCity}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">具体地址:</text>
				<text>{{museum.museumAddress}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">开放时间:</text>
				<text>{{museum.museumTime}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">博物馆介绍:</text>
			</view>
			<text style="margin-left: 10px;margin-bottom: 20px;margin-top: 5px;	padding-right: 10px;">{{museum.museumDetail}}</text>
		</view>
	</view>
</template>
<script>
	import TravelApi from '../../api/TravelApi.js'
export default {
	data() {
		return {
			museum:[],
			tagEvaluate:[],
			imgList:[],
			imgPrefix:'http://localhost:9527/app/museumImg/'
		}
	},
	methods: {
		getEvaluate(){
				let evaluation=this.museum.museumEvaluate
				this.tagEvaluate=evaluation.split('、')
			},
			getImgList(){
				this.imgList=this.museum.museumImg.split('/')
				this.imgList.pop()
			}
		},
		onLoad(e){
			TravelApi.museumDetail(e.museumID).then(res=>res.data).then(res=>{
				this.museum=res.data[0]
				this.getImgList()
				this.getEvaluate()
			})
		}
}
</script>


<style lang="scss">
.sceneryImg{
	width: 100%;
	height: 500rpx;
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
	position: absolute;
	top: 450rpx;
	border-top-left-radius:15px ;
	display: flex;
	flex-direction: column;
	background-color: antiquewhite;
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
