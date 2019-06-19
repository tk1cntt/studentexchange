import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/shopping-cart/shopping-cart.reducer';
import { encodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Order extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
    this.props.getOwnerEntities();
  }

  decreaseQuantity = (item: any) => {
    // console.log(item);
  };

  render() {
    const { shoppingCartList, account } = this.props;
    // console.log('shoppingCartList', shoppingCartList);
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="order-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            Noi dung phia tren
            <button className="btn btn-primary btn-block m-t">
              <i className="fa fa-shopping-cart" /> Đặt tất cả hàng
            </button>
          </div>
          <div className="wrapper wrapper-content animated fadeInRight ecommerce">
            <div className="ibox-content m-b-sm border-bottom">
              <div className="row">
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="order_id">
                      Order ID
                    </label>
                    <input type="text" id="order_id" name="order_id" defaultValue placeholder="Order ID" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="status">
                      Order status
                    </label>
                    <input type="text" id="status" name="status" defaultValue placeholder="Status" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="customer">
                      Customer
                    </label>
                    <input type="text" id="customer" name="customer" defaultValue placeholder="Customer" className="form-control" />
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="date_added">
                      Date added
                    </label>
                    <div className="input-group date">
                      <span className="input-group-addon">
                        <i className="fa fa-calendar" />
                      </span>
                      <input id="date_added" type="text" className="form-control" defaultValue="03/04/2014" />
                    </div>
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="date_modified">
                      Date modified
                    </label>
                    <div className="input-group date">
                      <span className="input-group-addon">
                        <i className="fa fa-calendar" />
                      </span>
                      <input id="date_modified" type="text" className="form-control" defaultValue="03/06/2014" />
                    </div>
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="amount">
                      Amount
                    </label>
                    <input type="text" id="amount" name="amount" defaultValue placeholder="Amount" className="form-control" />
                  </div>
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    <table
                      className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded"
                      data-page-size={15}
                    >
                      <thead>
                        <tr>
                          <th className="footable-visible footable-first-column footable-sortable">
                            Mã đơn hàng
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Sản phẩm
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Tổng tiền
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Ngày mua
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Trạng thái
                            <span className="footable-sort-indicator" />
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(3214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$500.00</td>
                          <td className="footable-visible">03/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(12345)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$320.00</td>
                          <td className="footable-visible">12/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(13214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$2770.00</td>
                          <td className="footable-visible">06/07/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(12346)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$8560.00</td>
                          <td className="footable-visible">01/12/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            642
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$6843.00</td>
                          <td className="footable-visible">10/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-success">Shipped</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(123467)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$750.00</td>
                          <td className="footable-visible">04/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-success">Shipped</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(23214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$500.00</td>
                          <td className="footable-visible">03/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            324
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$320.00</td>
                          <td className="footable-visible">12/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(33214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$2770.00</td>
                          <td className="footable-visible">06/07/2015</td>
                          <td className="footable-visible">
                            <span className="label label-danger">Canceled</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />${encodeId(34214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$8560.00</td>
                          <td className="footable-visible">01/12/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                      </tbody>
                      <tfoot>
                        <tr>
                          <td colSpan={7} className="footable-visible">
                            <ul className="pagination pull-right">
                              <li className="footable-page-arrow disabled">
                                <a data-page="first" href="#first">
                                  «
                                </a>
                              </li>
                              <li className="footable-page-arrow disabled">
                                <a data-page="prev" href="#prev">
                                  ‹
                                </a>
                              </li>
                              <li className="footable-page active">
                                <a data-page={0} href="#">
                                  1
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={1} href="#">
                                  2
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={2} href="#">
                                  3
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="next" href="#next">
                                  ›
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="last" href="#last">
                                  »
                                </a>
                              </li>
                            </ul>
                          </td>
                        </tr>
                      </tfoot>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-xs-12">
              <div className="row wrapper wrapper-content">
                {shoppingCartList.map((shoppingCart, ii) => (
                  <div key={`entity-${ii}`}>
                    <div className=".col-xs-12 col-md-8">
                      <div className="ibox float-e-margins">
                        <div className="ibox-title">
                          <h5>{`${shoppingCart.aliwangwang}`}</h5>
                          <div className="ibox-tools">
                            <span className="label label-warning-light pull-right">
                              {`${shoppingCart.items.length}`} mặt hàng trong giỏ
                            </span>
                          </div>
                        </div>
                        <div className="ibox-content">
                          <div>
                            <div className="feed-activity-list">
                              {shoppingCart.items.map((item, iy) => (
                                <div className="feed-element" key={`entity-${iy}`}>
                                  <a href="profile.html" className="pull-left">
                                    <img alt="image" className="img-circle" src={`${item.propertiesImage}`} />
                                  </a>
                                  <div className="media-body ">
                                    <small className="pull-right">
                                      <div className="input-group bootstrap-touchspin">
                                        <span className="input-group-btn">
                                          <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                            -
                                          </button>
                                        </span>
                                        <input type="tel" className="form-control quantity" min="0" defaultValue={`${item.quantity}`} />
                                        <span className="input-group-btn">
                                          <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                            +
                                          </button>
                                        </span>
                                      </div>
                                    </small>
                                    <strong>{`${item.itemName}`}</strong>
                                    <br />
                                    <small className="text-muted">
                                      Thuộc tính: {`${item.propertiesName}`}({`${item.propertiesType}`})<br />
                                      Số lượng: {`${item.quantity}`}
                                      <br />
                                      Đơn giá: ¥{`${item.itemPriceNDT}`}
                                    </small>
                                  </div>
                                </div>
                              ))}
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div className=".col-xs-12 col-md-4">
                      <div className="row checkout-cart-detail">
                        <button className="btn btn-primary btn-block">
                          <span className="checkout-cart">
                            <Link to={'/shopping-cart'}>
                              <i className="fa fa-shopping-cart" /> Đặt hàng
                            </Link>
                          </span>
                        </button>
                        <div className="col-xs-8 item">Tiền hàng:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí mua hàng:</div>
                        <div className="col-xs-4 item">
                          <b>2,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí kiểm đếm:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển nội địa TQ:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí đóng kiện gỗ:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển TQ - VN:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển nội địa VN:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                      </div>
                      <div className="row checkout-cart-detail checkout-cart-total">
                        <div className="col-xs-8 item">
                          <h4>Tổng tiền:</h4>
                        </div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
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
  shoppingCartList: storeState.shoppingCart.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getOwnerEntities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Order);
