<template>
	<view class="titel">
		<text class="mb-1">
			<uni-icons type="circle-filled" size="26" color="#ba5140" ></uni-icons>
			每日非遗
		</text>
		<!-- 轮播图 -->
		<view class="cultural_banner_box">
			<uni-swiper-dot class="uni-swiper-dot-box" :mode="mode">
				<swiper class="swiper"  
				:circular="true">
					<swiper-item v-for="(item,index) in topBanner" :key="index">
						<image class="banner" :src="item.img_url" mode="aspectFill"></image>
					</swiper-item>
				</swiper>
			</uni-swiper-dot>
		</view>
		
		<!--最新资讯 -->
		<text class="mb-1">
			<uni-icons type="circle-filled" size="26" color="#ba5140" ></uni-icons>
			最新资讯
		</text>
		<!-- 内容 -->
	   <!--  <view class="news">
			<uni-list>
				<uni-list-item title="列表左侧带略缩图" note="列表描述信息" showArrow
							   thumb="https://pics6.baidu.com/feed/b58f8c5494eef01f151ce22843f26d29bd317de8.jpeg@f_auto?token=719cf58bb130a7c341e2f861ff6c1444"
							   thumb-size="lg" rightText="大图" />
			</uni-list>
		</view> -->
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				topBanner:[
					{img_url:"../../static/推荐.jpg"},
					{img_url:"../../static/swiper/非遗5.png"},
					{img_url:"../../static/swiper/非遗6.png"}
				],
				mode: 'default'
			}
		},
		methods: {
			
		}
	}
</script>

<style lang="scss" scoped>
	.title {
			overflow: hidden;
		}
	.mb-1 {
			padding-top: 35px;
			font-size: 30px;
			font-family: "隶书";
			color: #ba5140;
			display: flex;
			flex-direction: row;
			justify-content:flex-start;
		}
	.cultural_banner_box {
			//top:250px;
			margin-top: 10px;
			display: flex;
			width: 100%;
			overflow: hidden;
			border-radius: 40px;
			.swiper{
				width: 100%;
				height: 400rpx;
				.banner{
					width: 100%;
					height: 400rpx;
				}
			}
		}
		.news{
			padding-top: 35px;
			width: 100%;
		}
</style>