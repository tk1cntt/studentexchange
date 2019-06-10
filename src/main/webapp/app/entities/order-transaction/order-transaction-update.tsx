import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrderCart } from 'app/shared/model/order-cart.model';
import { getEntities as getOrderCarts } from 'app/entities/order-cart/order-cart.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './order-transaction.reducer';
import { IOrderTransaction } from 'app/shared/model/order-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrderTransactionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOrderTransactionUpdateState {
  isNew: boolean;
  orderCartId: string;
  approverId: string;
  createById: string;
  updateById: string;
}

export class OrderTransactionUpdate extends React.Component<IOrderTransactionUpdateProps, IOrderTransactionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      orderCartId: '0',
      approverId: '0',
      createById: '0',
      updateById: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getOrderCarts();
    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { orderTransactionEntity } = this.props;
      const entity = {
        ...orderTransactionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/order-transaction');
  };

  render() {
    const { orderTransactionEntity, orderCarts, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.orderTransaction.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.orderTransaction.home.createOrEditLabel">
                Create or edit a OrderTransaction
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : orderTransactionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="order-transaction-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="amountLabel" for="amount">
                    <Translate contentKey="studentexchangeApp.orderTransaction.amount">Amount</Translate>
                  </Label>
                  <AvField id="order-transaction-amount" type="string" className="form-control" name="amount" />
                </AvGroup>
                <AvGroup>
                  <Label id="noteLabel" for="note">
                    <Translate contentKey="studentexchangeApp.orderTransaction.note">Note</Translate>
                  </Label>
                  <AvField id="order-transaction-note" type="text" name="note" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="studentexchangeApp.orderTransaction.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="order-transaction-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && orderTransactionEntity.status) || 'DEPOSIT'}
                  >
                    <option value="DEPOSIT">
                      <Translate contentKey="studentexchangeApp.TransactionType.DEPOSIT" />
                    </option>
                    <option value="ORDER_PAYMENT">
                      <Translate contentKey="studentexchangeApp.TransactionType.ORDER_PAYMENT" />
                    </option>
                    <option value="REFUND">
                      <Translate contentKey="studentexchangeApp.TransactionType.REFUND" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="orderDateLabel" for="orderDate">
                    <Translate contentKey="studentexchangeApp.orderTransaction.orderDate">Order Date</Translate>
                  </Label>
                  <AvField id="order-transaction-orderDate" type="date" className="form-control" name="orderDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.orderTransaction.createAt">Create At</Translate>
                  </Label>
                  <AvField id="order-transaction-createAt" type="date" className="form-control" name="createAt" />
                </AvGroup>
                <AvGroup>
                  <Label id="updateAtLabel" for="updateAt">
                    <Translate contentKey="studentexchangeApp.orderTransaction.updateAt">Update At</Translate>
                  </Label>
                  <AvField id="order-transaction-updateAt" type="date" className="form-control" name="updateAt" />
                </AvGroup>
                <AvGroup>
                  <Label for="orderCart.id">
                    <Translate contentKey="studentexchangeApp.orderTransaction.orderCart">Order Cart</Translate>
                  </Label>
                  <AvInput id="order-transaction-orderCart" type="select" className="form-control" name="orderCartId">
                    <option value="" key="0" />
                    {orderCarts
                      ? orderCarts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="approver.login">
                    <Translate contentKey="studentexchangeApp.orderTransaction.approver">Approver</Translate>
                  </Label>
                  <AvInput id="order-transaction-approver" type="select" className="form-control" name="approverId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="createBy.login">
                    <Translate contentKey="studentexchangeApp.orderTransaction.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="order-transaction-createBy" type="select" className="form-control" name="createById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="updateBy.login">
                    <Translate contentKey="studentexchangeApp.orderTransaction.updateBy">Update By</Translate>
                  </Label>
                  <AvInput id="order-transaction-updateBy" type="select" className="form-control" name="updateById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/order-transaction" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  orderCarts: storeState.orderCart.entities,
  users: storeState.userManagement.users,
  orderTransactionEntity: storeState.orderTransaction.entity,
  loading: storeState.orderTransaction.loading,
  updating: storeState.orderTransaction.updating,
  updateSuccess: storeState.orderTransaction.updateSuccess
});

const mapDispatchToProps = {
  getOrderCarts,
  getUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderTransactionUpdate);
