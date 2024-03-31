<template>
	<view class="help_home">
		<view class="user_menu_box">
			<uni-list>
				<uni-list-item clickable link to="/pages/help/helpforget" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon1" title="如何修改密码" />
				<uni-list-item clickable link to="/pages/user/collect" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon2" title="如何修改订单" />
				<uni-list-item clickable link to="/pages/user/setting" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon3" title="如何修改昵称" />
				<uni-list-item clickable link to="/pages/help/help" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon4" title="定位不准确" />
				<uni-list-item clickable link to="/pages/help/help" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon5" title="如何绑定银行卡" />
				<uni-list-item clickable link to="/pages/help/help" @click='onClick' :show-extra-icon="true" showArrow :extra-icon="Icon6" title="如何批量删除购物车" />
			</uni-list>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				Icon1:{
					color:'#ba5140',
					size:'22',
					type:'gear-filled'
				},
				Icon2:{
					color:'#ba5140',
					size:'22',
					type:'cart'
				},
				Icon3:{
					color:'#ba5140',
					size:'22',
					type:'staff-filled'
				},
				Icon4:{
					color:'#ba5140',
					size:'22',
					type:'location'
				},
				Icon5:{
					color:'#ba5140',
					size:'22',
					type:'wallet-filled'
				},
				Icon6:{
					color:'#ba5140',
					size:'22',
					type:'clear'
				},
			}
		},
		methods: {
			onClick(){
				
			}
		}
	}
</script>

<style>

</style>
