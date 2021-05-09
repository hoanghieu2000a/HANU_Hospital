import React, { Component } from 'react'
import { withRouter, Route, Redirect } from 'react-router-dom';
import { checkStillLogin } from '../utils/utils'
class ProtectedRoute extends Component {

    checkAccess() {
        const { access, role } = this.props;
        if (access && role) {
            for (let i = 0; i < access.length; i++) {
                if (access[i] === role) {
                    return true;
                }
            }
            return false
        } else {
            return true
        }

    }

    render() {
        const { path, children, exact } = this.props;
        return <Route exact={exact} to={path}>
            {this.checkAccess() ? "" : <Redirect push to="/" />}
            {checkStillLogin() ? children : <Redirect push to="/login" />}
        </Route>
    }
}

export default withRouter(ProtectedRoute);