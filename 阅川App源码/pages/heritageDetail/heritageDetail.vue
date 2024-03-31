<template>
	<view class="heritageDetail">
		<view class="heritageImg">
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
		
		<view class="heritageInfor">
			<view class="inforLine" style="margin-top: 20px;">
				<text style="font-weight: bold;font-size: 20px;">{{heritage.heritageName}}</text>
			</view>
			<view class="inforLine">
				<uni-icons type="heart-filled" size="25" v-if="isCollection" @click="delCollect()"></uni-icons>
				<uni-icons type="heart" size="25" v-if="!isCollection" @click="addCollect()"></uni-icons>
				
				<uni-icons type="star" size="25"></uni-icons>
				<uni-icons type="redo-filled" size="25"></uni-icons>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">非遗类别:</text>
				<text style="margin-left: 10px;">{{heritage.heritageCategory}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">非遗属地</text>
				<text style="margin-left: 10px;">{{heritage.heritageCity}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">申遗单位</text>
				<text style="margin-left: 10px;">{{heritage.heritageAddress}}</text>
			</view>
			<view class="inforLine">
				<text style="font-size: 15px;font-weight: bold">申遗时间</text>
				<text style="margin-left: 10px;">{{heritage.applicationTime}}</text>
			</view>
			<text style="font-size: 15px;font-weight: bold">非遗介绍</text>
			<text style="margin-top: 5px;margin-right: 10px;">{{heritage.heritageDetail}}</text>
			<text style="margin-top: 20px;font-size: 15px;font-weight: bold ">相关推荐</text>
			<view class="recommend">
				<view 
				class="recommendInfor" v-for="recommend in recommendList" :key="recommend.heritageName"
				@click="toRecommend(recommend.heritageID)"
				>
					<image :src="imgPrefix+recommend.heritageImg" mode="aspectFill"></image>
					<text>{{recommend.heritageName}}</text>
				</view>
			</view>
			
		</view>
	</view>
</template>

<script>
	import HeritageApi from '../../api/HeritageApi.js'
import MarketApi from '../../api/MarketApi.js'
	import PersonalApi from '../../api/PersonalApi.js'
	export default {
		data() {
			return {
				heritage:[],
				tagEvaluate:[],
				imgList:[],
				CurUser:[],
				searchMap:{
					heritageName:'',
					heritageCategory:'',
					heritageCity:'',
					earliestTime:'',
					latestTime:''
				},
				collection:{
					userID:'',
					heritageID:'',
				},
				recommendList:[],
				imgPrefix:'http://localhost:9527/app/heritageImg/',
				isCollection:false
			}
		},
		methods: {
			getImgList(){
				this.imgList=this.heritage.heritageImg.split('/')
				this.imgList.pop()
			},
			toRecommend(heritageID){
				uni.navigateTo({
					url:'/pages/heritageDetail/heritageDetail?heritageID='+heritageID
				})
			},
			getRecommend(){
				HeritageApi.allHeritage(this.searchMap).then(res=>res.data).then(res=>{
					for(const key in res.data){
						if (res.data[key].heritageName!=this.heritage.heritageName){
							const imgList=res.data[key].heritageImg.split('/')
							res.data[key].heritageImg=imgList[0]
							this.recommendList.push(res.data[key])
						}
						if(this.recommendList.length==3) break
					}
				})
			},
			isCollect(userID,heritageID){
				PersonalApi.isCollection(userID,heritageID).then(res=>res.data).then(res=>{
					console.log(res.code)
					if(res.code===200){
						this.isCollection=true
					}else{
						this.isCollection=false
					}
				})
			},
			addCollect(){
				PersonalApi.addCollection(this.CurUser.userID,this.heritage.heritageID).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `添加收藏成功`,
						icon: 'none'
					})
					this.getHeritage(this.heritage.heritageID)
				})
			},
			delCollect(){
				PersonalApi.delCollection(this.CurUser.userID,this.heritage.heritageID).then(res=>res.data).then(res=>{
					uni.showToast({
						title: `取消收藏成功`,
						icon: 'none'
					})
					this.getHeritage(this.heritage.heritageID)
				})
			},
			getHeritage(heritageID){
				HeritageApi.heritageDetail(heritageID).then(res=>res.data).then(res=>{
					this.heritage=res.data[0]
					this.searchMap.heritageCategory=this.heritage.heritageCategory
					this.getImgList()
					this.getRecommend()
					this.isCollect(this.CurUser.userID,heritageID)
				})
			}
		},
		onLoad(e){
			this.getHeritage(e.heritageID)
		},
		mounted(){
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
		}
	}
</script>

<style lang="scss">
.heritageDetail{
	display: flex;
	flex-direction: column;
}
.heritageImg{
	width: 100%;
	height: 500rpx;
	background-color: antiquewhite;
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
.heritageInfor{
	width: 100%;
	position: absolute;
	top: 450rpx;
	background-color: antiquewhite;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	display: flex;
	flex-direction: column;
	.uni-icons{
		margin-top: 5px;
	}
	text{
		margin-left: 20px;
	}
	.inforLine{
		display: flex;
		flex-direction: row;
		margin-bottom: 5px;
	}
	.recommend{
		display: flex;
		flex-direction: row;
		margin-bottom: 20px;
		.recommendInfor{
			width: 225rpx;
			height: 220rpx;
			background-color: #f5f2e9;
			border: 1px solid #d4d3ca;
			margin-left: 8px;
			margin-top: 5px;
			display: flex;
			flex-direction: column;
			image{
				width: 135rpx;
				height: 135rpx;
				border-radius: 50%;
				margin: 7px auto;
			}
			text{
				font-size: 10px; 
				width: 225rpx;
				margin-left: 0px;
				text-align: center;
			}
		}
	}
}
</style>
