import React from 'react'

function Name(props) {
	return <h1>网站名称：{props.name}</h1>;
}
function Url(props) {
	return <h1>网站地址：{props.url}</h1>;
}
function Nickname(props) {
	return <h1>网站小名：{props.nickname}</h1>;
}

class Complex extends React.Component {
	render() {
		return (
			<div>
				<Name name="菜鸟教程" />
				<Url url="http://www.runoob.com" />
				<Nickname nickname="Runoob" />
			</div>
		)
	}
}

export default Complex
