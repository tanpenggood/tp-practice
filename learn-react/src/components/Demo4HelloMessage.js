import React from 'react'
/**
 * state 和 props 主要的区别在于 props 是不可变的，而 state 可以根据与用户交互来改变。
 * 这就是为什么有些容器组件需要定义 state 来更新和修改数据。
 * 而子组件只能通过 props 来传递数据。
 *
 * @description:
 * @author: tanpeng
 * @date : 2020-01-20 22:58
 * @version: v1.0.0
 */
class HelloMessage extends React.Component {
    render() {
        return (
            <h1>Hello, {this.props.name}</h1>
        );
    }
}

/**
 * @description: 默认 Props
 * 通过组件类的 defaultProps 属性为 props 设置默认值
 * @author: tanpeng
 * @date : 2020-01-20 22:59
 * @version: v1.0.0
 */
HelloMessage.defaultProps = {
    name: 'Runoob'
}

export default HelloMessage