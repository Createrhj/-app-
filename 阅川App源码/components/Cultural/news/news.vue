<template>
	<view >
		<uni-list class="content" >
			<!-- title、note设置标题和小文字 -->
			<uni-list-item direction="row" v-for="item in info" :key="item.id" :title="item.title" :note="item.user_name + ' '+item.last_modify_date">
				<!-- 通过v-slot:header插槽定义列表左侧的图片显示，其他内容通过List组件内置属性实现-->
				<template v-slot:header>
					<!-- 当前判断长度只为简单判断类型，实际业务中，根据逻辑直接渲染即可 -->
					<image class="image-1" :src="item.avatar" mode="aspectFill"></image>
				</template>
			</uni-list-item>
		</uni-list>

		<!-- 通过 loadMore 组件实现上拉加载效果，如需自定义显示内容，可参考：https://ext.dcloud.net.cn/plugin?id=29 -->
		<uni-load-more  :status="statusLoadMore" />

	</view>
</template>

<script>
	export default {

		data() {
			return {				
				statusLoadMore:"more",//more,loading,nomore
				info: [
					{
						"user_name": "珙县地方志",
						"title": "珙县非遗登上央视",
						"avatar": "https://img1.baidu.com/it/u=700210703,188067492&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1698080400&t=914fdbe801589f7d0e814214dd5acf6b",
						"last_modify_date": "2023-10-20 17:37:51",
					},
					{
						"user_name": "中国经济新闻",
						"title": "第八届中国成都国际非物质文化遗产节邛酒文化周开幕",
						"avatar": "https://pic.wy6000.com/upload/image/rw/doc/20231014/word_3043_f39a5426e42578e43d3ed5cf12d8b0da.png",
						"last_modify_date": "2023-10-16 17:54:51",
					},
					{
						"user_name": "四川省地方志",
						"title": "苴却砚的古与今‖程学勇",
						"avatar": "http://www.scdfz.org.cn/Upload/main/ContentManage/Article/image/abbe103c2915494a81b4e9117872db61.jpeg",
						"last_modify_date": "2023-10-14 14:46:18",
					},
					{
						"user_name": "四川省地方志",
						"title": "吼号子 唱山歌 哼小调——川渝地区劳作号子在金堂县五凤古镇唱响",
						"avatar": "http://www.scdfz.org.cn/Upload/main/ContentManage/Article/image/e3f04cb374ac45c0b829debcf1227923.jpeg",
						"last_modify_date": "023-10-14 14:50:51",
					},
					{
						"user_name": "新华网",
						"title": "成都国际非遗节：让传统非遗焕发新活力",
						"avatar": "http://www.xinhuanet.com/2023-10/13/1129914316_16971832980031n.jpg",
						"last_modify_date": "2023-10-13 15:47:01",
					},
					
					 {
						"user_name": "四川日报",
						"title": "麻柳刺绣：指尖上的国家非遗",
						"avatar": "https://img0.baidu.com/it/u=3647993649,2376491062&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=888",
						"last_modify_date": "2023-10-11 12:35:21",
					}, {
						"user_name": "四川非遗",
						"title": "领略安岳石刻的唐风宋韵",
						"avatar": "http://www.scdfz.org.cn/Upload/main/ContentManage/Article/image/5584b50b52c84b8fa3d4d6bb2fc99bb3.jpeg",
						"last_modify_date": "2023-08-11 16:31:03",
					},
				]
			}
		},
		onLoad() {

		},
		methods: {
			/**
			 * 下拉刷新回调函数
			 */
		
			/**
			 * 上拉加载更多
			 */
			onReachBottom() {
				console.log("上拉加载更多")
				// 显示加载中
				this.statusLoadMore = "loading"
				var that = this

				setTimeout(function() {	
					// 显示没有更多数据了
					that.statusLoadMore = "nomore"
				}, 2000)
			},
		}
	}
</script>

<style lang="scss" scoped>
	/deep/ .uni-list-item{
		background-color: #f2ebd9 !important;
	}
	
	/deep/ .uni-list-item__content-title{
		color: #ba5b49;
	}
	/deep/ .uni-list-item__content-note[data-v-296a3d7e] {
		color: #bdcbd2;
	}
	/deep/ .uni-list-item__container[data-v-296a3d7e] {
		padding:0;
		padding-top: 15px;
	} 

	.image-1 {
		flex-shrink: 0;
		margin-right: 10px;
		width: 125px;
		height: 75px;
		border-radius: 6px;
		overflow: hidden;
		border: 1px #f5f5f5 solid;
	}
</style>