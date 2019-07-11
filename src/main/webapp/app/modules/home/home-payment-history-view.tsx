import React from 'react';
import { connect } from 'react-redux';

import { getOwnerEntities as getShoppingItem } from 'app/entities/shopping-cart-item/shopping-cart-item.reducer';
import { PaymentType } from 'app/shared/model/payment.model';
import { formatCurency, getLabelFromNumber } from 'app/shared/util/utils';
import { TextFormat } from 'react-jhipster';
import { APP_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentHistoryProp extends StateProps, DispatchProps {}

export class PaymentHistory extends React.Component<IPaymentHistoryProp> {
  render() {
    return (
      <>
        <h4>5 lịch sử giao dịch</h4>
        <ul className="list-group clear-list m-t">
          {this.props.paymentList.length === 0 ? (
            <li className="list-group-item fist-item">Chưa có giao dịch</li>
          ) : (
            <>
              {this.props.paymentList.map((payment, i) => (
                <li className="list-group-item">
                  <span className="pull-right">
                    <small>
                      <TextFormat type="date" value={payment.createAt} format={APP_DATE_FORMAT} />
                    </small>
                  </span>
                  <span className={`label ${getLabelFromNumber(i + 1)}`}>{i + 1}</span>
                  {payment.type === PaymentType.ORDER_PAYMENT
                    ? payment.note
                    : payment.type === PaymentType.DEPOSIT
                    ? `Nạp ${formatCurency(payment.amount)}đ vào tài khoản`
                    : `Hoàn ${formatCurency(payment.amount)}đ vào tài khoản`}
                </li>
              ))}
            </>
          )}
        </ul>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  paymentList: storeState.payment.entities
});

const mapDispatchToProps = { getShoppingItem };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PaymentHistory);
