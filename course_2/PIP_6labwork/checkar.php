<?php

function Check($x,$y,$r){
	
	 $isOk = preg_match('/^[0-9]$/', $r);
     $isOk = $isOk && ($r <= 5 && $r >= 1);
     $isOk = $isOk && preg_match('/^([+-])?[0-9](\.[0-9])?$/', $x);
     $isOk = $isOk && $x <= 2 && $x >= -2;
     $isOk = $isOk && preg_match('/^([+-])?[0-9](\.[0-9]+)?$/', $y);
     $isOk = $isOk && $y <= 3 && $y >= -3;
     return($isOk);
}


function Area($x,$y,$r){
  if($x >0 && $y<=0 && ($x*$x+$y*$y)<$r*$r){
  return true;
  }
  else if($x<=0 && $y<0 && -$x<$r/2 && -$y<$r){
    return true;
  }
  else if($x>0 && $y>0 && $y<$r/2 && $x<$r && ($x*$x+$y*$y)<($r/2*$r/2+$r*$r)){
    return true;
  }
  return false;
  }
?>

<?php

$X = $_GET['X'];
$Y = $_GET['Y'];
$R = $_GET['R'];
?>
<tr>
					<td><?php echo $X; ?></td>
      				<td><?php echo $Y; ?></td>
       				<td><?php echo $R; ?></td>
       				<td><?php 
			$start = microtime(true);
			if(Check($X, $Y, $R) == 1){
					 if(Area($X, $Y, $R)){
				          echo 'Yes'; }
				     else{
				          echo 'No'; } }
			else{
				echo'Incorrect data';}
			$time = (microtime(true) - $start)*pow(10,6);
            ?></td>
        <td><?php echo date(' H:i:s'); ?></td>
        <td><?php echo round($time,4); ?></td>
    
	
	</tr>


