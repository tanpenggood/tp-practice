import React from 'react'

class NumberList extends React.Component{

    /**
     * @description: 1、初始化
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