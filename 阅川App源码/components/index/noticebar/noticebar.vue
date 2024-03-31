<template>
	<view>
		<uni-notice-bar showIcon
		 scrollable 
		 single 
		 :text="news"
		 style="background-color: #f2ebd9; border-top-left-radius: 20px; border-top-right-radius: 20px;">
			
		</uni-notice-bar>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				news:"文旅APP"
			}
		}
	}
</script>

<style>
</style>