import React from 'react';
import { TextFormat } from 'react-jhipster';

import { PaymentType } from 'app/shared/model/payment.model';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { formatCurency } from 'app/shared/util/utils';

export interface IPaymentListProp {
  paymentList: any;
  isUser: boolean;
}

export class PaymentList extends React.Component<IPaymentListProp> {
  render() {
    return (
      <div className="ibox">
        <div className="ibox-content">
          {this.props.paymentList.length === 0 ? (
            <div className="no-content">Không có dữ liệu</div>
          ) : (
            <table className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded" data-page-size={15}>
              <thead>
                <tr>
                  {this.props.isUser ? <th /> : <th>Khách hàng</th>}
                  <th>Mã giao dịch</th>
                  <th>Số tiền</th>
                  <th>Số dư sau giao dịch</th>
                  <th>Loại giao dịch</th>
                  <th>Nội dung</th>
                </tr>
              </thead>
              <tbody>
                {this.props.paymentList.map((payment, i) => (
                  <tr key={`id-${i}`} className="footable-even" style={{}}>
                    {this.props.isUser ? <td /> : <td>{payment.customerLogin}</td>}
                    <td>
                      {payment.code}
                      <br />
                      <small>
                        <TextFormat type="date" value={payment.createAt} format={APP_DATE_FORMAT} />
                      </small>
                    </td>
                    <td>
                      {payment.type === PaymentType.ORDER_PAYMENT ? (
                        <b className="text-danger">-{formatCurency(payment.amount)}đ</b>
                      ) : (
                        <b className="text-info">+{formatCurency(payment.amount)}đ</b>
                      )}
                    </td>
                    <td>{formatCurency(payment.newBalance)}đ</td>
                    <td>{payment.type}</td>
                    <td>{payment.note}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    );
  }
}

export default PaymentList;
