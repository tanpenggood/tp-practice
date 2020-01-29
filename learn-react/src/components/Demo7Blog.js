import React from 'react'

/**
 * 元素的 key 在他的兄弟元素之间应该唯一
 * @description:
 * 数组元素中使用的 key 在其兄弟之间应该是独一无二的。
 * 然而，它们不需要是全局唯一的。
 * 当我们生成两个不同的数组时，我们可以使用相同的键。
 * @author: tanpeng
 * @date : 2020-01-29 19:59
 * @version: v1.0.0
 */
class Blog extends React.Component {

    /**
     * @description: 1、声明并初始化
     * @author: tanpeng
     * @date : 2020-01-29 19:57
     * @version: v1.0.0
     */
    constructor() {
        super()
        this.state = {posts: []}
    }

    /**
     * @description: 2、赋值
     * @author: tanpeng
     * @date : 2020-01-29 19:57
     * @version: v1.0.0
     */
    componentDidMount() {
        this.setState({
            posts: [
                {id: 1, title: 'Hello World', content: 'Welcome to learning React!'},
                {id: 2, title: 'Installation', content: 'You can install React from npm.'}
            ]
        })
    }

    /**
     * 1、兄弟元素 key 唯一，但不需要全局唯一
     * 2、JSX 允许在 {} 中嵌入任何表达式，所以我们可以这样使用 map()
     * @description: 获取侧边栏
     * @author: tanpeng
     * @date : 2020-01-29 20:00
     * @version: v1.0.0
     */
    getSidebar() {
        const posts = this.state.posts
        return (
            <ul>
                {posts.map(post =>
                    <li key={post.id}>{post.title}</li>
                )}
            </ul>
        )
    }

    /**
     * 兄弟元素 key 唯一，但不需要全局唯一
     * @description: 获取内容
     * @author: tanpeng
     * @date : 2020-01-29 20:01
     * @version: v1.0.0
     */
    getContent() {
        return (
            this.state.posts.map(post =>
                <div key={post.id}>
                    <h3>{post.title}</h3>
                    <p>{post.content}</p>
                </div>
            )
        )
    }

    render() {
        return (
            <div>
                {this.getSidebar()}
                <hr/>
                {this.getContent()}
            </div>
        )
    }

}

export default Blog