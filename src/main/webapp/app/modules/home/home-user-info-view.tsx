import React from 'react';
import { formatCurency } from 'app/shared/util/utils';

export interface IUserInfoProp {
  userBalanceEntity: any;
  temporaryPassword: any;
}

export class UserInfo extends React.Component<IUserInfoProp> {
  render() {
    return (
      <>
        <h4>Thông tin tài khoản</h4>
        <ul className="list-group clear-list m-t">
          <li className="list-group-item fist-item">
            <span className="pull-right">{formatCurency(this.props.userBalanceEntity.balanceAvailable)}đ</span>
            <i className="fa fa-money" /> Số dư tài khoản
          </li>
          <li className="list-group-item fist-item">{this.props.temporaryPassword}</li>
        </ul>
      </>
    );
  }
}

export default UserInfo;
