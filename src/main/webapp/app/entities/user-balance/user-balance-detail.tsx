import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-balance.reducer';
import { IUserBalance } from 'app/shared/model/user-balance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserBalanceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UserBalanceDetail extends React.Component<IUserBalanceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userBalanceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.userBalance.detail.title">UserBalance</Translate> [<b>{userBalanceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="balanceAvailable">
                <Translate contentKey="studentexchangeApp.userBalance.balanceAvailable">Balance Available</Translate>
              </span>
            </dt>
            <dd>{userBalanceEntity.balanceAvailable}</dd>
            <dt>
              <span id="balanceFreezing">
                <Translate contentKey="studentexchangeApp.userBalance.balanceFreezing">Balance Freezing</Translate>
              </span>
            </dt>
            <dd>{userBalanceEntity.balanceFreezing}</dd>
            <dt>
              <span id="cash">
                <Translate contentKey="studentexchangeApp.userBalance.cash">Cash</Translate>
              </span>
            </dt>
            <dd>{userBalanceEntity.cash}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.userBalance.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={userBalanceEntity.createAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.userBalance.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={userBalanceEntity.updateAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="studentexchangeApp.userBalance.createBy">Create By</Translate>
            </dt>
            <dd>{userBalanceEntity.createByLogin ? userBalanceEntity.createByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/user-balance" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/user-balance/${userBalanceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ userBalance }: IRootState) => ({
  userBalanceEntity: userBalance.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserBalanceDetail);
