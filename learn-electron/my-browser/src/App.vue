<template>
	<div id="app">
		<input class="search-input" v-model="q"/>
		<ul>
			<li><a href="https://github.com/tanpenggood" target="_blank">Github</a></li>
			<li><a href="https://gitee.com/tanpenggood" target="_blank">Gitee</a></li>
			<li><a href="http://showdoc.itplh.com" target="_blank">ShowDoc</a></li>
			<li><a href="https://blog.csdn.net/AV_woaijava" target="_blank">CSDN 花田小苏</a></li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'App',
		data() {
			return {
				q: 'showdoc.itplh.com',
			}
		},
		created() {
			window.addEventListener('keydown', this.handleClickEnter, true)
		},
		methods: {
			/**
			 * 处理按下Enter键
			 *
			 * @author: tanpeng
			 * @date: 2020/9/9 17:53
			 */
			handleClickEnter(e) {
				const key = window.event === undefined ? e.keyCode : window.event.keyCode
				console.log(key)
				if (key === 13) {
					this.handleSearch(this.q)
				}
			},
			/**
			 * 处理搜索
			 *
			 * @author: tanpeng
			 * @date: 2020/9/9 17:53
			 */
			handleSearch(q) {
				if (q.startsWith('http://') || q.startsWith('https://')) {
					const questionMarkIndex = q.indexOf('?')
					if (questionMarkIndex === -1) {
						window.open(q)
					} else {
						const link = q.substring(0, questionMarkIndex)
						let params = encodeURIComponent(q.substring(questionMarkIndex + 1))
						window.open(`${link}?${params}`)
					}
				} else {
					window.open(`http://www.baidu.com/s?wd=${encodeURIComponent(q)}`)
				}
			}
		}
	}
</script>

<style>
	#app {
		width: 100%;
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-color: #f5f5d5;
	}

	.search-input {
		border: aqua 5px solid;
		border-radius: 10px;
		width: 400px;
		height: 40px;
		font-size: 22px;
		padding: 5px;
		text-align: center;
	}

	ul {
		list-style: none;
	}

	ul > li {
		margin-bottom: 16px;
	}

	a {
		text-decoration: none;
		color: cornflowerblue;
		font-size: 16px;
	}
</style>
