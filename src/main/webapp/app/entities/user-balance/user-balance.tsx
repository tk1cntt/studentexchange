import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './user-balance.reducer';
import { IUserBalance } from 'app/shared/model/user-balance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserBalanceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class UserBalance extends React.Component<IUserBalanceProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { userBalanceList, match } = this.props;
    return (
      <div>
        <h2 id="user-balance-heading">
          <Translate contentKey="studentexchangeApp.userBalance.home.title">User Balances</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="studentexchangeApp.userBalance.home.createLabel">Create new User Balance</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.balanceAvailable">Balance Available</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.balanceFreezing">Balance Freezing</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.cash">Cash</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.createAt">Create At</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.updateAt">Update At</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.userBalance.createBy">Create By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userBalanceList.map((userBalance, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${userBalance.id}`} color="link" size="sm">
                      {userBalance.id}
                    </Button>
                  </td>
                  <td>{userBalance.balanceAvailable}</td>
                  <td>{userBalance.balanceFreezing}</td>
                  <td>{userBalance.cash}</td>
                  <td>
                    <TextFormat type="date" value={userBalance.createAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={userBalance.updateAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{userBalance.createByLogin ? userBalance.createByLogin : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userBalance.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userBalance.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userBalance.id}/delete`} color="danger" size="sm">
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
      </div>
    );
  }
}

const mapStateToProps = ({ userBalance }: IRootState) => ({
  userBalanceList: userBalance.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserBalance);
