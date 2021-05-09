import React, { Component } from 'react'
import BreadCrumbs from '../../components/breadcrumbs/BreadCrumbs';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { billProfit, billIncome, billExpend } from '../../utils/api'
const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Accounting"
}

class Bill extends Component {

    constructor(props) {
        super(props)
        this.state = {
            income: null,
            expense: null,
            profit: null
        }
    }

    async componentDidMount() {
        const profit = await billProfit();
        const income = await billIncome();
        const expend = await billExpend();
        this.setState({
            income: income,
            expense: expend,
            profit: profit
        })
    }

    render() {
        const { income, expense, profit } = this.state;
        return <div className="bill full">
            <BreadCrumbs data={data} />
            <section id="bill-section" className="main-section">
                <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">INCOME</TableCell>
                                <TableCell align="left">EXPEND</TableCell>
                                <TableCell align="left">PROFIT</TableCell>
                            </TableRow>
                        </TableHead>
                        {income && expense && profit && <TableBody>
                            <TableCell align="left"><h1 className="big-text" style={{ color: 'blue' }}> $ {income} </h1></TableCell>
                            <TableCell align="left"><h1 className="big-text" style={{ color: 'red' }}> $ {expense} </h1></TableCell>
                            <TableCell align="left"><h1 className="big-text" style={{ color: 'green' }}> $ {profit} </h1></TableCell>
                        </TableBody>}
                    </Table>
                </TableContainer>
            </section>
        </div>
    }
}

export default Bill