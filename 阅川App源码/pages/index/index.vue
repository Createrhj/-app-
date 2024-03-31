<template>
	<view class="sum">
		<indexRotationchart/>
		<Navbar/>
		<Noticebar/>
		<Grid/>
		<Section/>
		
	</view>
</template>

<script>
	import Navbar from "../../components/index/navbar/navbar.vue"
	import IndexRotationchart from "../../components/index/indexRotationchart/indexRotationchart.vue"
	import Noticebar from "../../components/index/noticebar/noticebar.vue"
	import Grid from "../../components/index/indexgrid/indexgrid.vue"
	import Section from "../../components/index/section/section.vue"
	
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		components:{
			IndexRotationchart,
			Navbar,
			Noticebar,
			Grid,
			Section
		}
	}
</script>

<style lang="scss">
	
</style>
