<template>
	<view class="goodsSearch">
		<uni-search-bar radius="100" placeholder="其输入搜索内容" clearButton="none" />
		<view class="goodsTotal">
			<view class="filter"style="font-size: 16px;margin-top: -15px;margin-bottom: 10px;">
				<text style="color: #631216;font-weight: bold;">综合</text>
				<text style="color: #631216;font-weight: bold;">价格</text>
				<text style="color: #631216;font-weight: bold;">销量</text>
				<text @click="openPopup" style="color: #631216;font-weight: bold;">筛选</text>
			</view>
			<view class="goodsList" @click="toTheaterDetail(theater.theaterID)" v-for="theater in theaterData" :key="theater.theaterID">
				<image :src="imgPrefix+theater.theaterImg" mode="aspectFill"></image>
				<view class="goodsInfor">
					<view class="inforList" style="margin-top: 5px;font-weight: bold;">
						<text>{{theater.theaterName}}</text>
					</view>
					<view class="inforList" style="margin-top: 10px;">
						<uni-tag 
						style="background-color: #cb523e; border-color: #cb523e; color: #fff;"
						:inverted="true" :text="theater.theaterEvaluate" type="error" size="small"/>
					</view>
					<view class="inforList" style="margin-bottom: 0px;">
						<text>{{theater.theaterTime}}</text>
					</view>
					<view class="inforList">
						<text>{{theater.theaterCity}}</text>
						<text class="price">￥{{theater.theaterPrice}}</text>
					</view>
				</view>
			</view>

		</view>
		
		<uni-popup ref="popup" type="top">
			<view class="filterPopup">
				<text>剧场时间</text>
				<view class="filterType">
					<input placeholder="自定义最低价"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="自定义最高价"/>
				</view>
				<text style="margin-top: 20px;">剧场价格</text>
				<view class="filterType">
					<input placeholder="自定义最低价"/>
					<uni-icons type="arrow-right" size="20"></uni-icons>
					<input placeholder="自定义最高价"/>
				</view>
				<text style="margin-top: 20px;">剧场地点</text>
				<view class="filterType">
					<button>成都</button>
					<button>绵阳</button>
					<button>乐山</button>
				</view>
				<view class="filterType">
					<button>宜宾</button>
					<button>凉山</button>
					<button>甘孜</button>
				</view>
				<view class="filterType">
					<button class="btn">取消</button>
					<button class="btn">确定筛选</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
import TravelApi from '../../api/TravelApi.js'
	export default {
		data() {
			return {
				theaterData:[],
				searchMap:{
					theaterName:'',
					theaterCategory:'',
					theaterCity:'',
					lowPrice:'',
					highPrice:'',
				},
				imgPrefix:'http://localhost:9527/app/theaterImg/'
			}
		},
		methods: {
			openPopup(){
			// 通过组件定义的ref调用uni-popup方法 ,如果传入参数 ，type 属性将失效 ，仅支持 ['top','left','bottom','right','center']
			this.$refs.popup.open()
			},
			allTheater(){
				TravelApi.allTheater(this.searchMap).then(res=>res.data).then(res=>{
					this.theaterData=res.data
					for (const key in this.theaterData){
						const imgList=this.theaterData[key].theaterImg.split('/')
						this.theaterData[key].theaterImg=imgList[0]
					}
				})
			},
			toTheaterDetail(theaterID){
				uni.navigateTo({
					url: '/pages/theaterDetail/theaterDetail?theaterID='+theaterID
				})
			},
		},
		mounted(){
			this.allTheater()
		}
	}
</script>

<style lang="scss">
	text{
		color: #683632;
	}
button::after{ border: none;} 
.btn{
	margin-left: 15px;
	margin-right: 10Apx;
	margin-top: 20px;
	width: 150px;
	height: 40px;
	border-radius: 10px;
	font-size: 17px
}
button{
	width: 80px;
	height: 30px;
	margin: 5px 0px 0px 10px;
	border-radius: 20px;
	border:0px;
	font-size: 13px;
	color: #EAEEF1;
	background-color: #CB523E;
}
input{
	color: #EAEEF1;
	background-color: #CB523E;
	width: 120px;
	height: 30px;
	margin-right: 10px;
	margin-left: 20px;
	margin-top: 5px;
	text-align: center;
	border-radius: 15px;
}
.filterPopup{
	width: 100%;
	height: 750rpx;
	padding-top: 10px;
	background-color: #FFFFFF;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 15px;
	display: flex;
	flex-direction: column;
	text{
		margin-left: 10px;
		margin-top: 10px;
		font-size: 16px;
		font-weight: bold;
	}
	.filterType{
		display: flex;
		flex-direction: row;
		margin-left: 10px;
		margin-top: 5px;
	}
}
.goodsSearch{
	display: flex;
	flex-direction: column;
}
.typeTitle{
	font-size: 25px;
	font-weight: bold;
	text-align: center;
	margin-top: 10px;
}
.goodsTotal{
	display: flex;
	flex-direction: column;
	width: 100%;
	margin-top: 10px;
	background-color: antiquewhite;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	.filter{
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px 50px 10px 50px;
	}
	.goodsList{
		width: 100%;
		margin-left: 10px;
		display: flex;
		flex-direction: row;
		margin-bottom: 15px;
		image{
			width: 130px;
			height: 130px;
			border-radius: 15px;
		}
		.goodsInfor{
			display: flex;
			flex-direction: column;
			.uni-tag{
				margin-right: 5px;
			}
			.inforList{
				margin-left: 10px;
				margin-bottom: 5px;
			}
			.price{
				font-weight: bold;
				font-size: 20px;
				color: #b93a26;
				margin-left: 55px;
			}
		}
	}
}
</style>
