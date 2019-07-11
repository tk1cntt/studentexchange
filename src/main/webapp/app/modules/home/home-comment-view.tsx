// import './home.scss';
import axios from 'axios';
import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import { Spin } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities as getShoppingItem } from 'app/entities/shopping-cart-item/shopping-cart-item.reducer';
import { getOwnerEntities as getOwnerPayment } from 'app/entities/payment/payment.reducer';
import { decodeId } from 'app/shared/util/utils';
import { AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import PaymentHistory from './home-payment-history-view';
import UserInfo from './home-user-info-view';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface IHomeState {
  temporaryPassword: any;
}
export class Home extends React.Component<IHomeProp> {
  state: IHomeState = {
    temporaryPassword: null
  };

  componentDidMount() {}

  render() {
    return (
      <div className="ibox float-e-margins">
        <div className="ibox-title">
          <h5>Trao đổi thông tin</h5>
          <div className="ibox-tools">
            <a className="collapse-link">
              <i className="fa fa-chevron-up" />
            </a>
            <a className="dropdown-toggle" data-toggle="dropdown" href="#">
              <i className="fa fa-wrench" />
            </a>
            <ul className="dropdown-menu dropdown-user">
              <li>
                <a href="#">Config option 1</a>
              </li>
              <li>
                <a href="#">Config option 2</a>
              </li>
            </ul>
            <a className="close-link">
              <i className="fa fa-times" />
            </a>
          </div>
        </div>
        <div className="ibox-content no-padding">
          <ul className="list-group">
            <li className="list-group-item">
              <p>
                <a className="text-info" href="#">
                  @Alan Marry
                </a>{' '}
                I belive that. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
              </p>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 1 minuts ago
              </small>
            </li>
            <li className="list-group-item">
              <p>
                <a className="text-info" href="#">
                  @Stock Man
                </a>{' '}
                Check this stock chart. This price is crazy!{' '}
              </p>
              <div className="text-center m">
                <span id="sparkline8" />
              </div>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 2 hours ago
              </small>
            </li>
            <li className="list-group-item">
              <p>
                <a className="text-info" href="#">
                  @Kevin Smith
                </a>{' '}
                Lorem ipsum unknown printer took a galley{' '}
              </p>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 2 minuts ago
              </small>
            </li>
            <li className="list-group-item ">
              <p>
                <a className="text-info" href="#">
                  @Jonathan Febrick
                </a>{' '}
                The standard chunk of Lorem Ipsum
              </p>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 1 hour ago
              </small>
            </li>
            <li className="list-group-item">
              <p>
                <a className="text-info" href="#">
                  @Alan Marry
                </a>{' '}
                I belive that. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
              </p>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 1 minuts ago
              </small>
            </li>
            <li className="list-group-item">
              <p>
                <a className="text-info" href="#">
                  @Kevin Smith
                </a>{' '}
                Lorem ipsum unknown printer took a galley{' '}
              </p>
              <small className="block text-muted">
                <i className="fa fa-clock-o" /> 2 minuts ago
              </small>
            </li>
          </ul>
        </div>
      </div>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
