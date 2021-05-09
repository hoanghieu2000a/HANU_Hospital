import React, { Component } from 'react'
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import Typography from '@material-ui/core/Typography';
import { Link as Linker } from 'react-router-dom'

// const sample = {
//     active: [{ title: "Homepage", link: "dashboard" }],
//     current: "Test"
// }

class BreadCrumbs extends Component {
    render() {
        const { data } = this.props;
        return <div>
            <Typography variant="h6" style={{ color: "green", fontWeight: '600' }}>{data.current}</Typography>
            <Breadcrumbs aria-label="breadcrumb" separator="›" >
                {data.active.map(bread => {
                    return <Linker key={bread.title} to={bread.link}>
                        <p color="textSecondary" >
                            {bread.title}
                        </p>
                    </Linker>
                })}
                <Typography color="textPrimary">{data.current}</Typography>
            </Breadcrumbs>
        </div>
        // return <Breadcrumbs aria-label="breadcrumb">
        //     <Link color="inherit" href="" >
        //         Material-UI
        // </Link>
        //     <Link color="inherit" href="/getting-started/installation/" >
        //         Core
        // </Link>
        //     <Typography color="textPrimary">Breadcrumb</Typography>
        // </Breadcrumbs>
    }
}

export default BreadCrumbs;
