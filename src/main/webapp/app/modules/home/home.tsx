// import './home.scss';
import axios from 'axios';
import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import { Spin } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities as getShoppingItem } from 'app/entities/shopping-cart-item/shopping-cart-item.reducer';
import { getOwnerEntities as getOwnerPayment } from 'app/entities/payment/payment.reducer';
import { PaymentType } from 'app/shared/model/payment.model';
import { formatCurency, getLabelFromNumber, decodeId } from 'app/shared/util/utils';
import { TextFormat } from 'react-jhipster';
import { APP_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface IHomeState {
  temporaryPassword: any;
}
export class Home extends React.Component<IHomeProp> {
  state: IHomeState = {
    temporaryPassword: null
  };

  componentDidMount() {
    this.props.getShoppingItem();
    this.props.getOwnerPayment(0, 5, 'createAt');
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

          <div onClick={this.getTemporaryPassword}>
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
    const { account } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="dashboard" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row border-bottom white-bg dashboard-header">
            <div className="col-md-8">
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
            </div>
            <div className="col-md-4">
              <h4>Thông tin tài khoản</h4>
              <ul className="list-group clear-list m-t">
                <li className="list-group-item fist-item">
                  <span className="pull-right">{formatCurency(this.props.userBalanceEntity.balanceAvailable)}đ</span>
                  <i className="fa fa-money" /> Số dư tài khoản
                </li>
                <li className="list-group-item fist-item">{this.state.temporaryPassword}</li>
              </ul>
            </div>
          </div>
          <div className="row">
            <div className="col-lg-12">
              <div className="wrapper wrapper-content">
                <div className="row">
                  <div className="col-lg-4">
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
                  </div>
                  <div className="col-lg-4">
                    <div className="ibox float-e-margins">
                      <div className="ibox-title">
                        <h5>Giỏ hàng</h5>
                        <div className="ibox-tools">
                          <span className="label label-warning-light pull-right">10 mặt hàng trong giỏ</span>
                        </div>
                      </div>
                      <div className="ibox-content">
                        <div>
                          <div className="feed-activity-list">
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">5m ago</small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">5m ago</small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                          </div>
                          <button className="btn btn-primary btn-block m-t">
                            <span className="checkout-cart">
                              <Link to={'/shopping-cart'}>
                                <i className="fa fa-shopping-cart" /> Xem giỏ hàng
                              </Link>
                            </span>
                          </button>
                        </div>
                      </div>
                    </div>
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
  userBalanceEntity: storeState.userBalance.entity,
  paymentList: storeState.payment.entities
});

const mapDispatchToProps = { getSession, getShoppingItem, getOwnerPayment };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
