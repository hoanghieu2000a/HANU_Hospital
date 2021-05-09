import React, { Component } from 'react'
import { withRouter, Route } from 'react-router-dom';
import { checkStillLogin } from '../utils/utils'

class LoginRoute extends Component {
    componentDidMount() {
        console.log("unauthen")
    }

    render() {
        const { path, children, exact } = this.props;
        if (checkStillLogin()) {
            this.props.history.goBack();
            return null;
        } else {
            return <Route exact={exact} to={path}>
                {children}
            </Route>
        }
    }
}

export default withRouter(LoginRoute);