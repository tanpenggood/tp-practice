import React from 'react'

/**
 * @description:
 * JSX优点：
 *  1、JSX 执行更快，因为它在编译为 JavaScript 代码后进行了优化。
 *  2、它是类型安全的，在编译过程中就能发现错误。
 *  3、使用 JSX 编写模板更加简单快速。
 *
 * 使用 JSX {}
 *  1、最外层只能有一个 HTML 标签
 *  2、给 HTML 标签添加自定义的属性需要使用 data- 前缀，如：<p data-myattribute="somevalue">自定义p标签属性</p>
 *  3、JSX 中不能使用 if else 语句
 *  4、JSX 中可以使用 conditional (三元运算) 表达式。
 *  5、样式 React 推荐使用内联样式。我们可以使用 camelCase(驼峰) 语法来设置内联样式. React 会在指定元素数字后自动添加 px 。
 *      如：
 *          var myStyle = { fontSize: 100, color: '#FF0000' }
 *          <h1 style={myStyle}>菜鸟教程</h1>
 *  6、注释需要写在花括号中
 *  7、数组 JSX 允许在模板中插入数组，数组会自动展开所有成员
 * @author: tanpeng
 * @date : 2020-01-29 20:12
 * @version: v1.0.0
 */
class JSXComponent extends React.Component {

    render() {
        const myStyle = { fontSize: 24, color: '#FF0000', }
        const arr = [
            <h1>菜鸟教程</h1>,
            <h2>学的不仅是技术，更是梦想！</h2>,
            <div style={myStyle}>自定义样式</div>,
        ]
        return (
            <div>
                {arr}
                {/*JSX中注释演示...*/}
            </div>
        )
    }
}

export default JSXComponent