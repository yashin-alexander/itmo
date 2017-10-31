`timescale 1ns / 1ps

module message_mode(
input clk,
input rst,
input SW,
output txd,
output [7:0] word,
output [9:0] counter,
output transmit_ready
);

reg [103:0] string = "Hello world!\n";
reg [9:0] counter = 0;
reg connection_status = 1;
reg [7:0] char = 0; 
    
transmit uut1 (
    .word(word), 
    .clk(clk), 
    .rst(rst),
    .connection_status(connection_status),
    .txd(txd),
    .transmit_ready(transmit_ready)
);

always @(posedge clk)
    // message
    if (SW)
        begin
            if (counter > 103)
                connection_status = 0;
            if (counter == 1000)
                begin
                    counter = 0;
                    connection_status = 1;
                    string = "Hello world!\n";
                end
            else
                if (transmit_ready)
                    begin
                        char = string[103:96];
                        string = string << 8;
                        counter = counter + 1;
                    end
            end

assign word = char;
        
endmodule
