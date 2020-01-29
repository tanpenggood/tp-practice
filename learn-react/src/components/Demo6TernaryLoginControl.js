import React from 'react'

class TernaryLoginControl extends React.Component {
    constructor(props) {
        super(props)
        this.state = {isLoggedIn: false}
        this.handleLoginClick = this.handleLoginClick.bind(this)
        this.handleLogoutClick = this.handleLogoutClick.bind(this)
    }

    handleLoginClick() {
        this.setState({isLoggedIn: true})
    }

    handleLogoutClick() {
        this.setState({isLoggedIn: false})
    }

    render() {
        const isLoggedIn = this.state.isLoggedIn

        return (
            <div>
                {isLoggedIn ? <UserGreeting/> : <GuestGreeting/>}
                {isLoggedIn ? < LogoutButton onClick={this.handleLogoutClick}/> :
                    <LoginButton onClick={this.handleLoginClick}/>}
            </div>
        )
    }
}

function UserGreeting(props) {
    return <h1>欢迎回来!</h1>
}

function GuestGreeting(props) {
    return <h1>请先注册。</h1>
}

function LoginButton(props) {
    return (
        <button onClick={props.onClick}>
            登陆
        </button>
    )
}

function LogoutButton(props) {
    return (
        <button onClick={props.onClick}>
            退出
        </button>
    )
}

export default TernaryLoginControl