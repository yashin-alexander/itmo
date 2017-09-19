`timescale 1ns / 1ps
module LED_logic(mode_clock, LD);
	input mode_clock;
	output [15:0] LD;
	reg signed [16:0] LED = 17'sb1_0000_0000_0000_0000;
	reg direction = 0;
	
	always @(posedge mode_clock) begin
		if(LED[15] == LED[0])
			direction = ~direction;
		if(direction)
			LED = LED >>> 1;
		else
			LED = LED << 1;
		end
		assign LD = LED;
endmodule
