import React from 'react'

class WebSite extends React.Component {
    constructor() {
        super()
        this.state = {
            name: "菜鸟教程",
            site: "https://www.runoob.com"
        }
    }
    render() {
        return (
            <div>
                <Name name={this.state.name} />
                <Link site={this.state.site} />
            </div>
        );
    }
}

class Name extends React.Component {
    render() {
        return (
            <h1>{this.props.name}</h1>
        );
    }
}

class Link extends React.Component {
    render() {
        return (
            <a href={this.props.site}>
                {this.props.site}
            </a>
        );
    }
}

export default WebSite