<template>
	<view class="box-bg">
		<uni-search-bar 
		placeholder="请输入搜索内容" 
		bgColor="#fff" 
		@confirm="search" 
		clearButton="auto" 
		cancelButton="auto">
		</uni-search-bar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchValue: ''
			}
		},
		methods: {
			search(res) {
				uni.showToast({
					title: '搜索：' + res.value,
					icon: 'none'
				})
			},
			input(res) {
				console.log('----input:', res)
			},
			clear(res) {
				uni.showToast({
					title: 'clear事件，清除值为：' + res.value,
					icon: 'none'
				})
			},
			blur(res) {
				uni.showToast({
					title: 'blur事件，输入值为：' + res.value,
					icon: 'none'
				})
			},
			focus(e) {
				uni.showToast({
					title: 'focus事件，输出值为：' + e.value,
					icon: 'none'
				})
			},
			cancel(res) {
				uni.showToast({
					title: '点击取消，输入值为：' + res.value,
					icon: 'none'
				})
			}
		},
		onBackPress() {
			// #ifdef APP-PLUS
			plus.key.hideSoftKeybord();
			// #endif
		}
	}
</script>

<style lang="scss" scoped>

	/deep/.uni-searchbar__box-search-input{
		color: #B3B3B3!important;
	}
	/deep/.uni-searchbar__cancel{
		color: #B3B3B3!important;
	}
	/deep/.uni-searchbar__box-search-input{
		color: #B3B3B3!important;
	}
	/deep/.uni-searchbar {
		padding: 2px;
	}
	

	.search-result {
		padding-top: 10px;
		padding-bottom: 20px;
		text-align: center;
	}

	.example-body {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 0px;
	}

</style>

