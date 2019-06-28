import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './payment.reducer';
import { IPayment } from 'app/shared/model/payment.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaymentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPaymentUpdateState {
  isNew: boolean;
  staffApprovalId: string;
  staffCancelId: string;
  createById: string;
}

export class PaymentUpdate extends React.Component<IPaymentUpdateProps, IPaymentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      staffApprovalId: '0',
      staffCancelId: '0',
      createById: '0',
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

    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { paymentEntity } = this.props;
      const entity = {
        ...paymentEntity,
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
    this.props.history.push('/entity/payment');
  };

  render() {
    const { paymentEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.payment.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.payment.home.createOrEditLabel">Create or edit a Payment</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : paymentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="payment-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="amountLabel" for="amount">
                    <Translate contentKey="studentexchangeApp.payment.amount">Amount</Translate>
                  </Label>
                  <AvField id="payment-amount" type="string" className="form-control" name="amount" />
                </AvGroup>
                <AvGroup>
                  <Label id="codeLabel" for="code">
                    <Translate contentKey="studentexchangeApp.payment.code">Code</Translate>
                  </Label>
                  <AvField id="payment-code" type="string" className="form-control" name="code" />
                </AvGroup>
                <AvGroup>
                  <Label id="newBalanceLabel" for="newBalance">
                    <Translate contentKey="studentexchangeApp.payment.newBalance">New Balance</Translate>
                  </Label>
                  <AvField id="payment-newBalance" type="string" className="form-control" name="newBalance" />
                </AvGroup>
                <AvGroup>
                  <Label id="noteLabel" for="note">
                    <Translate contentKey="studentexchangeApp.payment.note">Note</Translate>
                  </Label>
                  <AvField id="payment-note" type="text" name="note" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderCodeLabel" for="orderCode">
                    <Translate contentKey="studentexchangeApp.payment.orderCode">Order Code</Translate>
                  </Label>
                  <AvField id="payment-orderCode" type="text" name="orderCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="methodLabel">
                    <Translate contentKey="studentexchangeApp.payment.method">Method</Translate>
                  </Label>
                  <AvInput
                    id="payment-method"
                    type="select"
                    className="form-control"
                    name="method"
                    value={(!isNew && paymentEntity.method) || 'CASH'}
                  >
                    <option value="CASH">
                      <Translate contentKey="studentexchangeApp.PaymentMethod.CASH" />
                    </option>
                    <option value="BANK_TRANSFER">
                      <Translate contentKey="studentexchangeApp.PaymentMethod.BANK_TRANSFER" />
                    </option>
                    <option value="CARD">
                      <Translate contentKey="studentexchangeApp.PaymentMethod.CARD" />
                    </option>
                    <option value="PAYPAL">
                      <Translate contentKey="studentexchangeApp.PaymentMethod.PAYPAL" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="typeLabel">
                    <Translate contentKey="studentexchangeApp.payment.type">Type</Translate>
                  </Label>
                  <AvInput
                    id="payment-type"
                    type="select"
                    className="form-control"
                    name="type"
                    value={(!isNew && paymentEntity.type) || 'DEPOSIT'}
                  >
                    <option value="DEPOSIT">
                      <Translate contentKey="studentexchangeApp.PaymentType.DEPOSIT" />
                    </option>
                    <option value="ORDER_PAYMENT">
                      <Translate contentKey="studentexchangeApp.PaymentType.ORDER_PAYMENT" />
                    </option>
                    <option value="REFUND">
                      <Translate contentKey="studentexchangeApp.PaymentType.REFUND" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="studentexchangeApp.payment.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="payment-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && paymentEntity.status) || 'PENDING'}
                  >
                    <option value="PENDING">
                      <Translate contentKey="studentexchangeApp.PaymentStatusType.PENDING" />
                    </option>
                    <option value="PAID">
                      <Translate contentKey="studentexchangeApp.PaymentStatusType.PAID" />
                    </option>
                    <option value="CANCELED">
                      <Translate contentKey="studentexchangeApp.PaymentStatusType.CANCELED" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.payment.createAt">Create At</Translate>
                  </Label>
                  <AvField id="payment-createAt" type="date" className="form-control" name="createAt" />
                </AvGroup>
                <AvGroup>
                  <Label id="withdrawalFeeLabel" for="withdrawalFee">
                    <Translate contentKey="studentexchangeApp.payment.withdrawalFee">Withdrawal Fee</Translate>
                  </Label>
                  <AvField id="payment-withdrawalFee" type="string" className="form-control" name="withdrawalFee" />
                </AvGroup>
                <AvGroup>
                  <Label for="staffApproval.login">
                    <Translate contentKey="studentexchangeApp.payment.staffApproval">Staff Approval</Translate>
                  </Label>
                  <AvInput id="payment-staffApproval" type="select" className="form-control" name="staffApprovalId">
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
                  <Label for="staffCancel.login">
                    <Translate contentKey="studentexchangeApp.payment.staffCancel">Staff Cancel</Translate>
                  </Label>
                  <AvInput id="payment-staffCancel" type="select" className="form-control" name="staffCancelId">
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
                    <Translate contentKey="studentexchangeApp.payment.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="payment-createBy" type="select" className="form-control" name="createById">
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
                <Button tag={Link} id="cancel-save" to="/entity/payment" replace color="info">
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
  users: storeState.userManagement.users,
  paymentEntity: storeState.payment.entity,
  loading: storeState.payment.loading,
  updating: storeState.payment.updating,
  updateSuccess: storeState.payment.updateSuccess
});

const mapDispatchToProps = {
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
)(PaymentUpdate);
