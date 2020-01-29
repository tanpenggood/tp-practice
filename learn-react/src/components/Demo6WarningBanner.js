import React from 'react'

class Page extends React.Component {

    constructor(props) {
        super(props)
        this.state = {showWarning: false}
        this.handleToggleClick = this.handleToggleClick.bind(this)
    }

    handleToggleClick() {
        this.setState(prevState => ({
            showWarning: !prevState.showWarning
        }))
    }

    render() {
        return (
            <div>
                <WarningBanner warn={this.state.showWarning}/>
                <button onClick={this.handleToggleClick}>
                    {this.state.showWarning ? '隐藏' : '显示'}
                </button>
            </div>
        )
    }
}

/**
 * @description:
 * 组件的 render 方法返回 null 并不会影响该组件生命周期方法的回调。
 * 例如，componentWillUpdate 和 componentDidUpdate 依然可以被调用。
 * @author: tanpeng
 * @date : 2020-01-29 12:04
 * @version: v1.0.0
 */
function WarningBanner(props) {
    if (!props.warn) {
        return null
    }

    return (
        <div className="warning">
            警告！
        </div>
    )
}

export default Page