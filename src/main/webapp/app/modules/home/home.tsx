import './home.scss';

import React from 'react';
import { connect } from 'react-redux';

import { getSession } from 'app/shared/reducers/authentication';
import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
  }

  render() {
    const { account } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="dashboard" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <div className="col-md-3">
              <h2>Xin chào Công Công</h2>
              <small>Lịch sử thanh toán.</small>
              <ul className="list-group clear-list m-t">
                <li className="list-group-item fist-item">
                  <span className="pull-right">09:00 pm</span>
                  <span className="label label-success">1</span> Tôi muốn huỷ đơn hàng ABC31233
                </li>
                <li className="list-group-item">
                  <span className="pull-right">10:16 am</span>
                  <span className="label label-info">2</span> Đơn hàng của anh chị vừa chuyển về kho Trung Quốc
                </li>
                <li className="list-group-item">
                  <span className="pull-right">08:22 pm</span>
                  <span className="label label-primary">3</span> Bạn đã chuyển tiền thanh toán cho đơn hàng DH12345
                </li>
                <li className="list-group-item">
                  <span className="pull-right">11:06 pm</span>
                  <span className="label label-default">4</span> Bạn đã nạp 500.000 VNĐ vào tài khoản
                </li>
                <li className="list-group-item">
                  <span className="pull-right">12:00 am</span>
                  <span className="label label-primary">5</span> Đơn hàng DH12345 đã được mua
                </li>
              </ul>
            </div>
            <div className="col-md-6">
              <div className="flot-chart dashboard-chart">
                <div className="flot-chart-content" id="flot-dashboard-chart" />
              </div>
              <div className="row text-left">
                <div className="col-xs-4">
                  <div className=" m-l-md">
                    <span className="h4 font-bold m-t block">$ 406,100</span>
                    <small className="text-muted m-b block">Sales marketing report</small>
                  </div>
                </div>
                <div className="col-xs-4">
                  <span className="h4 font-bold m-t block">$ 150,401</span>
                  <small className="text-muted m-b block">Annual sales revenue</small>
                </div>
                <div className="col-xs-4">
                  <span className="h4 font-bold m-t block">$ 16,822</span>
                  <small className="text-muted m-b block">Half-year revenue margin</small>
                </div>
              </div>
            </div>
            <div className="col-md-3">
              <div className="statistic-box">
                <h4>Project Beta progress</h4>
                <p>You have two project with not compleated task.</p>
                <div className="row text-center">
                  <div className="col-lg-6">
                    <canvas id="doughnutChart2" width={80} height={80} style={{ margin: '18px auto 0' }} />
                    <h5>Kolter</h5>
                  </div>
                  <div className="col-lg-6">
                    <canvas id="doughnutChart" width={80} height={80} style={{ margin: '18px auto 0' }} />
                    <h5>Maxtor</h5>
                  </div>
                </div>
                <div className="m-t">
                  <small>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</small>
                </div>
              </div>
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
                            <i className="fa fa-arrow-down" /> Show More
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
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
