export class InteractiveArea {
  static get DEFAULT_STEP() {
    return 40;
  }

  constructor( radius, graphicContext, width, height ) {
    this.radius = radius * InteractiveArea.DEFAULT_STEP;
    this.gcontext = graphicContext;
    this.height = height;
    this.width = width;

    this.axis_color ="#000";
    this.fill_color = "#66608B";
  }

  setRadius( newRadius ) {
    this.radius = newRadius * InteractiveArea.DEFAULT_STEP;
    this.clearArea();
  }

  clearArea() {
    this._clearCanvas();
    this.drawArea();
  }

  drawArea() {
    this._drawRectPart();
    this._drawArcPart();
    this._drawTrianglePart();
    this.drawAxis( this.gcontext, this.width,this.height);
  }

  drawPoint( x, y, color = "#000" ) {

    this.gcontext.fillStyle = color;
    this.gcontext.beginPath();

    this.gcontext.arc( x, y, 3, 0, 2 * Math.PI  );

    this.gcontext.closePath();
    this.gcontext.stroke();
    this.gcontext.fill();
  }

  _clearCanvas() {
    this.gcontext.clearRect(
      0,
      0,
      this.width,
      this.height
    )
  }

  _drawRectPart() {
    this.gcontext.strokeStyle = this.axis_color;
    this.gcontext.fillStyle = this.fill_color;

    this.gcontext.fillRect( this.height/2, this.width/2, this.radius, this.radius);
    this.gcontext.strokeRect(this.height/2, this.width/2, this.radius, this.radius);
  }

  _drawArcPart() {
    this.gcontext.fillStyle = this.fill_color;

    this.gcontext.beginPath();

    this.gcontext.arc( this.width/2,this.height/2, this.radius / 2, 1.5*Math.PI, 2*Math.PI);
    this.gcontext.lineTo(this.height/2, this.width/2);
    this.gcontext.moveTo(this.height/2, this.width/2);

    this.gcontext.closePath();
    this.gcontext.fill();
    this.gcontext.stroke();
  }

  _drawTrianglePart() {
    this.gcontext.fillStyle = this.fill_color;

    this.gcontext.beginPath();

    this.gcontext.moveTo(this.width/2, this.height/2);
    this.gcontext.lineTo(this.width/2, this.height/2 + this.radius);
    this.gcontext.lineTo(this.width/2-this.radius/2, this.height/2);

    this.gcontext.fill();
    this.gcontext.closePath();
    this.gcontext.stroke();
  }
  drawAxis( graphicContext, width, height, color = "#000" ) {
    graphicContext.strokeStyle = color;

    graphicContext.beginPath();
    graphicContext.moveTo( width / 2, height );
    graphicContext.lineTo( width / 2 , 0);

    graphicContext.moveTo( 0.0, height / 2.0 );
    graphicContext.lineTo( width, height / 2.0 );
    graphicContext.stroke();
    graphicContext.closePath();
  }
}
