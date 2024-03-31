<template>
	<view class="container">
		<view class="second_container">
			<!-- <image src="../../static/back3.jpg" mode="scaleToFill" style="width: 100%;"> -->
				<uni-card class="card">
					<Choice/>
					<Top/>
					<News/>
				</uni-card>
			
		</view>
	</view>
</template>

<script>
	import Choice from "../../components/Cultural/choice/choice.vue" 
	import Top from "../../components/Cultural/card_top/card_vue.vue"
	import News from "../../components/Cultural/news/news.vue"
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		components:{
			Choice,
			Top,
			News
		},
		onShow() {
			uni.pageScrollTo({
				scrollTop: 0,
				duration: 0
			})
		}
	}
</script>

<style lang="scss" scoped>
	/deep/ .uni-card{
		border-top-left-radius: 50px !important;
		border-top-right-radius: 50px !important;
		margin-left: 0 !important;
		margin-right: 0 !important;
		background-color: #f2ebd9;
	}
	.container{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
		background-image: url("../../static/back10.jpg");
		background-repeat: no-repeat;
		background-size: 100% 100%;
		position: absolute;
		top: 0;
		.second_container{
			.card{
				text-align: left;
				padding: 15px 0px;
				top: 100px;
			}
		}
	}
	
</style>
