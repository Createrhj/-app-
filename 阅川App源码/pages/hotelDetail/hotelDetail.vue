<template>
	<view class="hotelDetail">
		<view class="hotelImg">
			<swiper class="swiper"
			:autoplay="true" 
			:interval="5000" 
			:duration="800"
			:circular="true">
				<swiper-item v-for="img in imgList" :key="img">
					<image :src="imgPrefix+img" mode="aspectFill"></image>
				</swiper-item>
			</swiper>
		</view>
		
		<view class="hotelInfor">
			<view class="inforLine">
				<text style="font-size: 20px;font-weight: bold;">{{hotel.hotelName}}</text>
			</view>
			<view class="inforLine">
				<uni-rate :touchable="false" :value="4"/>
				<text style="font-weight: bold;font-size: 20px;margin-left: 10px;">{{hotel.hotelCount}}</text>
				<text>8.6万人做出评价</text>
			</view>
			<view class="inforLine">
				<uni-tag 
				style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
				class="tag" :text="tag" type="primary" v-for="tag in tagEvaluate" :key="tag"></uni-tag>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">住宿类型:</text>
				<text>{{hotel.hotelCategory}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">所在城市:</text>
				<text>{{hotel.hotelCity}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">具体地址:</text>
				<text>{{hotel.hotelAddress}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">住宿价格:</text>
				<text>{{hotel.hotelPrice}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold;">酒店介绍:</text>
			</view>
			<text style="margin-left: 10px; margin-bottom: 20px;margin-top: 5px;padding-right: 10px;">{{hotel.hotelDetail}}</text>
		
		</view>
	</view>
</template>
<script>
import TravelApi from '../../api/TravelApi.js'
export default {
	data() {
		return {
			hotelID:'',
			hotel:[],
			tagEvaluate:[],
			imgList:[],
			imgPrefix:'http://localhost:9527/app/hotelImg/'
		}
	},
	methods: {
		getEvaluate(){
			let evaluation=this.hotel.hotelEvaluate
			this.tagEvaluate=evaluation.split('、')
		},
		getImgList(){
			this.imgList=this.hotel.hotelImg.split('/')
			this.imgList.pop()
		}
	},
	onLoad(e){
		TravelApi.hotelDetail(e.hotelID).then(res=>res.data).then(res=>{
			this.hotel=res.data[0]
			this.getImgList()
			this.getEvaluate()
		})
	}
}
</script>


<style lang="scss">
.hotelImg{
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
.hotelInfor{
	width: 100%;
	background-color: antiquewhite;
	position: absolute;
	top: 450rpx;
	border-top-left-radius:15px ;
	border-top-right-radius:15px ;
	display: flex;
	flex-direction: column;
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
