<template>
	<view class="Recommend">
		<uni-card title="基础卡片" padding="10px 0" >
			<template v-slot:title>
				<uni-list>
					<view class="title">
						<uni-icons type="vip" size="26" color="#a64036" ></uni-icons>
						<text >推荐项目</text>
					</view>
				</uni-list>
			</template>
			<view v-for="(item, index) in list" :key="index">
				<image style="width: 100%;" :src="item.src"></image>
			<text class="uni-body uni-mt-5">{{item.name}}</text>
			</view>
			

		</uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[
					{
						src: '../../static/top_index/蜀绣.png',
						name: '蜀绣'
					},
					{
						src: '../../static/top_index/川剧.png',
						name: '川剧'
					}
				]
			}
		}
	}
</script>

<style lang = "scss" scoped>
	/deep/ .uni-mt-5{
		text-align: center;
		font-size: 36upx !important;
		font-family: "楷体";
	}
	/deep/ .uni-card__content {
		color: #8b572a !important;
	}
	.Recommend{
		text-align: center;
		padding: 15px 0;
		margin: -5px;
	}
	.title{
		display: flex;
		text-align: center;
		font-size: 44upx;
		color:#a64036;
		font-weight: bold;
		font-family:"楷体";
		line-height: 80upx;
		/* height: 28px; */
		justify-content: center;
	}
</style>