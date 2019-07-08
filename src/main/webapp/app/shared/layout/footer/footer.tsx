import React from 'react';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <div className="footer">
          <div className="pull-right">
            10GB of <strong>250GB</strong> Free.
          </div>
          <div>
            <strong>Copyright</strong> Example Company © 2014-2017
          </div>
        </div>
      </Col>
    </Row>
  </div>
);

export default Footer;
