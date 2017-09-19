`timescale 1ns / 1ps
module LED_logic(ok, LEDs_strip);
	input ok;
	output [15:0] LEDs_strip;
	reg signed [16:0] LED = 17'sb1_0000_0000_0000_0000;
	reg direction = 0;
	
	always @(posedge ok) begin
		if(LED[15] == LED[0])
			direction = ~direction;
		if(direction)
			LED = LED >>> 1;
		else
			LED = LED << 1;
		end
		assign LEDs_strip = LED;
endmodule
