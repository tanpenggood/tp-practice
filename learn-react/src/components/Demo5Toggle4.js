import React from 'react'

class Toggle extends React.Component {
    constructor(props) {
        super(props);
        this.state = {isToggleOn: true};
    }

    // 事件对象e要放在最后
    handleClick(isToggleOn, e) {
        e.preventDefault()
        this.setState({
            isToggleOn: !isToggleOn
        })
    }

    render() {
        return (
            <button onClick={this.handleClick.bind(this, this.state.isToggleOn)}>
                4{this.state.isToggleOn ? 'ON' : 'OFF'}
            </button>
        );
    }
}

export default Toggle