<template>
	<view>
		<view class="order">
		
				<uni-card
				 style="background-color: #f5f2e9;border: 1px solid #d4d3ca;border-radius: 15px;height: 180px;" 
				 v-for="heritage in heritages" :key="heritage.heritageID">
					<view style="width: 100%;height: 130px;text-align: left">
				    <image mode="aspectFill" :src="imgPrefix+heritage.heritageImg" style="width: 40%;height: 120px;float: left;margin: 5px 10px 5px 5px"/>
				    <view style="margin:10px 0px 5px 5px">
						<text style="font-weight: bold;color: #683632;">{{heritage.heritageName}}</text>
				    </view>
				    <view style="margin-bottom: 10px;">

						 <uni-tag
						  style="background-color: #b93a26;border-color:#b93a26; color: #fff;"
						  :inverted="true" :text="heritage.heritageCategory" type="warning" size="small"/>
				    </view>
					<view>
					    <text style="color:#999;font-size: 13px;">
						{{heritage.heritageAddress}}
						</text>
					</view>
					<view>
					    <text style="color:#999;font-size: 13px;">
						{{heritage.applicationTime}}
						</text>
					</view>
					<!-- 按钮 -->
					<view style="margin-top: 5px;" @click="toHeritageDetail(heritage.heritageID)">
						<button plain="true" 
						style="border:1px solid #999; border-radius: 25px;justify-content: center;width: 95px;height: 30px;line-height: 50rpx;margin-bottom: 20px;margin-right: 1rpx;">
							<text style="font-size: 13px;">查看</text>
						</button>
					</view>
				    </view>
				  </uni-card>
			</view>
	</view>
</template>

<script>
	import PersonalApi from '../../api/PersonalApi.js'
	export default{
		data(){
			return{
				CurUser:[],
				heritages:[],
				imgPrefix:'http://localhost:9527/app/heritageImg/',
			}
		},
		methods:{
			collection(){
				PersonalApi.collection(this.CurUser.userID).then(res=>res.data).then(res=>{
					for(const key in res.data){
						const imgList=res.data[key].heritageImg.split('/')
						res.data[key].heritageImg=imgList[0]
					}
					this.heritages=res.data
					console.log(this.heritage)
				})
			},
			toHeritageDetail(heritageID){
				uni.navigateTo({
					url:'/pages/heritageDetail/heritageDetail?heritageID='+heritageID
				})
			},
		},
		mounted() {
			this.CurUser=JSON.parse(localStorage.getItem("CurUser"))
			this.collection()
		},
	}
</script>

<style>

</style>