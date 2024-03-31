<template>
	<view class="home">
		<view class="index_banner_box">
			<uni-swiper-dot class="uni-swiper-dot-box" :mode="mode">
				<swiper class="swiper" 
				:autoplay="true" 
				:interval="5000" 
				:duration="800"
				:circular="true">
					<swiper-item v-for="(item,index) in topBanner" :key="index">
						<image class="banner" :src="item.img_url" ></image>
					</swiper-item>
				</swiper>
			</uni-swiper-dot>
			
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				topBanner:[
					{img_url:"../../static/swiper/非遗4.png"},
					{img_url:"../../static/swiper/非遗5.png"},
					{img_url:"../../static/swiper/非遗3.jpg"}
				],
				mode: 'default'
			}
		},
		methods: {
			
		}
	}
</script>

<style lang="scss">
	.home{
		display: flex;
		flex-direction: column;
		flex :1;
		overflow: hidden;
		.index_banner_box{
			display: flex;
			width: 100%;
			overflow: hidden;
			.swiper{
				width: 100%;
				height: 420rpx;
				.banner{
					width: 100%;
					height: 420rpx;
				}
			}
		}
	}
</style>
