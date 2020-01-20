import React from 'react'

class Clock extends React.Component {
	constructor(props) {
		super(props);
		this.state = {date: new Date()};
	}

	/**
	 * @description: 挂载数据
	 * @author: tanpeng
	 * @date : 2020/1/20 17:33
	 * @version: v1.0.0
	 */
	componentDidMount() {
		this.timerID = setInterval(
			() => this.tick(),
			1000
		);
	}

	/**
	 * @description: 销毁数据
	 * @author: tanpeng
	 * @date : 2020/1/20 17:33
	 * @version: v1.0.0
	 */
	componentWillUnmount() {
		clearInterval(this.timerID);
	}

	tick() {
		this.setState({
			date: new Date()
		});
	}

	render() {
		return (
			<div>
				<h1>Hello, world!</h1>
				<h2>现在是 {this.state.date.toLocaleTimeString()}.</h2>
			</div>
		);
	}
}

export default Clock
