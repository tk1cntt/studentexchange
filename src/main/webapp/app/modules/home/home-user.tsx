// import './home.scss';
import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities as getOwnerPayment } from 'app/entities/payment/payment.reducer';
import { decodeId } from 'app/shared/util/utils';
import { AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import HomePaymentHistory from './home-payment-history-view';
import HomeUserInfo from './home-user-info-view';
import HomeComment from './home-comment-view';
import HomeShoppingCartItem from './home-shooping-cart-item-view';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getOwnerPayment(0, 5, 'createAt');
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="dashboard" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row border-bottom white-bg dashboard-header">
            <div className="col-md-8">
              <HomePaymentHistory />
            </div>
            <div className="col-md-4">
              <HomeUserInfo isUser={this.props.isUser} userBalanceEntity={this.props.userBalanceEntity} />
            </div>
          </div>
          <div className="row">
            <div className="col-lg-12">
              <div className="wrapper wrapper-content">
                <div className="row">
                  <div className="col-lg-4">
                    <HomeComment />
                  </div>
                  <div className="col-lg-4">
                    <HomeShoppingCartItem />
                  </div>
                  <div className="col-lg-4">
                    <div className="ibox float-e-margins">
                      <div className="ibox-title">
                        <h5>Alpha project</h5>
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
                      <div className="ibox-content ibox-heading">
                        <h3>Thông tin vận chuyển đơn hàng của bạn</h3>
                        <small>
                          <i className="fa fa-map-marker" /> Meeting is on 6:00am. Check your schedule to see detail.
                        </small>
                      </div>
                      <div className="ibox-content inspinia-timeline">
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-briefcase" />
                              6:00 am
                              <br />
                              <small className="text-navy">2 hour ago</small>
                            </div>
                            <div className="col-xs-7 content no-top-border">
                              <p className="m-b-xs">
                                <strong>Đang giao hàng</strong>
                              </p>
                              <p>
                                Conference on the sales results for the previous year. Monica please examine sales trends in marketing and
                                products. Below please find the current status of the sale.
                              </p>
                              <p>
                                <span data-diameter={40} className="updating-chart">
                                  5,3,9,6,5,9,7,3,5,2,5,3,9,6,5,9,4,7,3,2,9,8,7,4,5,1,2,9,5,4,7,2,7,7,3,5,2
                                </span>
                              </p>
                            </div>
                          </div>
                        </div>
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-file-text" />
                              7:00 am
                              <br />
                              <small className="text-navy">3 hour ago</small>
                            </div>
                            <div className="col-xs-7 content">
                              <p className="m-b-xs">
                                <strong>Yêu cầu giao</strong>
                              </p>
                              <p>
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                                industry's standard dummy text ever since.
                              </p>
                            </div>
                          </div>
                        </div>
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-coffee" />
                              8:00 am
                              <br />
                            </div>
                            <div className="col-xs-7 content">
                              <p className="m-b-xs">
                                <strong>Kho Việt Nam</strong>
                              </p>
                              <p>
                                Go to shop and find some products. Lorem Ipsum is simply dummy text of the printing and typesetting
                                industry. Lorem Ipsum has been the industry's.
                              </p>
                            </div>
                          </div>
                        </div>
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-phone" />
                              11:00 am
                              <br />
                              <small className="text-navy">21 hour ago</small>
                            </div>
                            <div className="col-xs-7 content">
                              <p className="m-b-xs">
                                <strong>Đang vận chuyển TQ-VN</strong>
                              </p>
                              <p>Lorem Ipsum has been the industry's standard dummy text ever since.</p>
                            </div>
                          </div>
                        </div>
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-user-md" />
                              09:00 pm
                              <br />
                              <small>21 hour ago</small>
                            </div>
                            <div className="col-xs-7 content">
                              <p className="m-b-xs">
                                <strong>Kho Trung Quốc</strong>
                              </p>
                              <p>Find some issue and go to doctor.</p>
                            </div>
                          </div>
                        </div>
                        <div className="timeline-item">
                          <div className="row">
                            <div className="col-xs-3 date">
                              <i className="fa fa-comments" />
                              12:50 pm
                              <br />
                              <small className="text-navy">48 hour ago</small>
                            </div>
                            <div className="col-xs-7 content">
                              <p className="m-b-xs">
                                <strong>Người bán giao hàng</strong>
                              </p>
                              <p>
                                Web sites still in their infancy. Various versions have evolved over the years, sometimes by accident,
                                sometimes on purpose (injected humour and the like).
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="footer">
                <div className="pull-right">
                  10GB of <strong>250GB</strong> Free.
                </div>
                <div>
                  <strong>Copyright</strong> Example Company © 2014-2017
                </div>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  isUser: hasAnyAuthority(storeState.authentication.account.authorities, [AUTHORITIES.USER]),
  userBalanceEntity: storeState.userBalance.entity
});

const mapDispatchToProps = { getSession, getOwnerPayment };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
