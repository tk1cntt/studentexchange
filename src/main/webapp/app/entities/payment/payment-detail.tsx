import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './payment.reducer';
import { IPayment } from 'app/shared/model/payment.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PaymentDetail extends React.Component<IPaymentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { paymentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.payment.detail.title">Payment</Translate> [<b>{paymentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="amount">
                <Translate contentKey="studentexchangeApp.payment.amount">Amount</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.amount}</dd>
            <dt>
              <span id="code">
                <Translate contentKey="studentexchangeApp.payment.code">Code</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.code}</dd>
            <dt>
              <span id="newBalance">
                <Translate contentKey="studentexchangeApp.payment.newBalance">New Balance</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.newBalance}</dd>
            <dt>
              <span id="note">
                <Translate contentKey="studentexchangeApp.payment.note">Note</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.note}</dd>
            <dt>
              <span id="orderCode">
                <Translate contentKey="studentexchangeApp.payment.orderCode">Order Code</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.orderCode}</dd>
            <dt>
              <span id="method">
                <Translate contentKey="studentexchangeApp.payment.method">Method</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.method}</dd>
            <dt>
              <span id="type">
                <Translate contentKey="studentexchangeApp.payment.type">Type</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.type}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="studentexchangeApp.payment.status">Status</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.status}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.payment.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={paymentEntity.createAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="withdrawalFee">
                <Translate contentKey="studentexchangeApp.payment.withdrawalFee">Withdrawal Fee</Translate>
              </span>
            </dt>
            <dd>{paymentEntity.withdrawalFee}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.payment.staffApproval">Staff Approval</Translate>
            </dt>
            <dd>{paymentEntity.staffApprovalLogin ? paymentEntity.staffApprovalLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.payment.staffCancel">Staff Cancel</Translate>
            </dt>
            <dd>{paymentEntity.staffCancelLogin ? paymentEntity.staffCancelLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.payment.customer">Customer</Translate>
            </dt>
            <dd>{paymentEntity.customerLogin ? paymentEntity.customerLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.payment.createBy">Create By</Translate>
            </dt>
            <dd>{paymentEntity.createByLogin ? paymentEntity.createByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/payment" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/payment/${paymentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ payment }: IRootState) => ({
  paymentEntity: payment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PaymentDetail);
