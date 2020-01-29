import React from 'react'

class NumberList extends React.Component{

    /**
     * @description: 1、声明并初始化
     * @author: tanpeng
     * @date : 2020-01-29 12:42
     * @version: v1.0.0
     */
    constructor() {
        super()
        this.state = {numbers: []}
    }

    /**
     * @description: 2、赋值
     * @author: tanpeng
     * @date : 2020-01-29 12:42
     * @version: v1.0.0
     */
    componentDidMount() {
        this.setState({
            numbers: [1,2,3,4,5]
        })
    }

    /**
     * @description: 3、转换
     * @author: tanpeng
     * @date : 2020-01-29 12:42
     * @version: v1.0.0
     */
    getListItems() {
        const numbers = this.state.numbers
        const listItems = numbers.map((num) =>
            // Keys 可以在 DOM 中的某些元素被增加或删除的时候帮助 React 识别哪些元素发生了变化。
            // 因此你应当给数组中的每一个元素赋予一个确定的标识key。
            <li key={num.toString()}>{num * 2}</li>
        )
        return listItems
    }

    /**
     * @description: 4、渲染（使用）
     * @author: tanpeng
     * @date : 2020-01-29 12:43
     * @version: v1.0.0
     */
    render() {
        return (
            <ul>{this.getListItems()}</ul>
        )
    }
}

export default NumberList