import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './payment.reducer';
import { IPayment } from 'app/shared/model/payment.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPaymentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPaymentState = IPaginationBaseState;

export class Payment extends React.Component<IPaymentProps, IPaymentState> {
  state: IPaymentState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { paymentList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="payment-heading">
          <Translate contentKey="studentexchangeApp.payment.home.title">Payments</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="studentexchangeApp.payment.home.createLabel">Create new Payment</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('amount')}>
                  <Translate contentKey="studentexchangeApp.payment.amount">Amount</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('code')}>
                  <Translate contentKey="studentexchangeApp.payment.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('newBalance')}>
                  <Translate contentKey="studentexchangeApp.payment.newBalance">New Balance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('note')}>
                  <Translate contentKey="studentexchangeApp.payment.note">Note</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('orderCode')}>
                  <Translate contentKey="studentexchangeApp.payment.orderCode">Order Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('method')}>
                  <Translate contentKey="studentexchangeApp.payment.method">Method</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('type')}>
                  <Translate contentKey="studentexchangeApp.payment.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('status')}>
                  <Translate contentKey="studentexchangeApp.payment.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createAt')}>
                  <Translate contentKey="studentexchangeApp.payment.createAt">Create At</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('withdrawalFee')}>
                  <Translate contentKey="studentexchangeApp.payment.withdrawalFee">Withdrawal Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.payment.staffApproval">Staff Approval</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.payment.staffCancel">Staff Cancel</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.payment.customer">Customer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.payment.createBy">Create By</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {paymentList.map((payment, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${payment.id}`} color="link" size="sm">
                      {payment.id}
                    </Button>
                  </td>
                  <td>{payment.amount}</td>
                  <td>{payment.code}</td>
                  <td>{payment.newBalance}</td>
                  <td>{payment.note}</td>
                  <td>{payment.orderCode}</td>
                  <td>
                    <Translate contentKey={`studentexchangeApp.PaymentMethod.${payment.method}`} />
                  </td>
                  <td>
                    <Translate contentKey={`studentexchangeApp.PaymentType.${payment.type}`} />
                  </td>
                  <td>
                    <Translate contentKey={`studentexchangeApp.PaymentStatusType.${payment.status}`} />
                  </td>
                  <td>
                    <TextFormat type="date" value={payment.createAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{payment.withdrawalFee}</td>
                  <td>{payment.staffApprovalLogin ? payment.staffApprovalLogin : ''}</td>
                  <td>{payment.staffCancelLogin ? payment.staffCancelLogin : ''}</td>
                  <td>{payment.customerLogin ? payment.customerLogin : ''}</td>
                  <td>{payment.createByLogin ? payment.createByLogin : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${payment.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${payment.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${payment.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ payment }: IRootState) => ({
  paymentList: payment.entities,
  totalItems: payment.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Payment);
