import axios from 'axios';
import React from 'react';
import { Spin } from 'antd';

import { formatCurency, decodeId } from 'app/shared/util/utils';

export interface IUserInfoProp {
  userBalanceEntity: any;
  isUser: boolean;
}

export interface IUserInfoStatus {
  temporaryPassword: any;
}

export class UserInfo extends React.Component<IUserInfoProp> {
  state: IUserInfoStatus = {
    temporaryPassword: null
  };

  componentDidMount() {
    if (this.props.isUser) {
      const temporaryPassword = (
        <div onClick={this.getTemporaryPassword}>
          <span className="label label-primary">
            <i className="fa fa-refresh" /> Lấy mật khẩu tạm thời
          </span>
        </div>
      );
      this.setState({
        temporaryPassword
      });
    }
  }

  getTemporaryPassword = () => {
    let temporaryPassword = <Spin />;
    this.setState({
      temporaryPassword
    });
    axios.post('api/account/temporary-password').then(response => {
      temporaryPassword = (
        <>
          <span className="pull-right">
            <span className="label label-danger" style={{ marginRight: 0 }}>
              {decodeId(response.data)}
            </span>
          </span>

          <div>
            <i className="fa fa-star" /> Mật khẩu tạm thời là
          </div>
        </>
      );
      this.setState({
        temporaryPassword
      });
    });
  };

  render() {
    return (
      <>
        <h4>Thông tin tài khoản</h4>
        <ul className="list-group clear-list m-t">
          <li className="list-group-item fist-item">
            <span className="pull-right">{formatCurency(this.props.userBalanceEntity.balanceAvailable)}đ</span>
            <i className="fa fa-money" /> Số dư tài khoản
          </li>
          <li className="list-group-item fist-item">{this.state.temporaryPassword}</li>
        </ul>
      </>
    );
  }
}

export default UserInfo;
