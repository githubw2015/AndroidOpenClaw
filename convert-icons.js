const sharp = require('sharp');
const fs = require('fs');
const path = require('path');

const svgPath = 'D:\\gitshort\\AndroidOpenClaw\\icon-preview.svg';
const baseResPath = 'D:\\gitshort\\AndroidOpenClaw\\app\\src\\main\\res';

const sizes = [
  { name: 'mdpi', size: 48 },
  { name: 'hdpi', size: 72 },
  { name: 'xhdpi', size: 96 },
  { name: 'xxhdpi', size: 144 },
  { name: 'xxxhdpi', size: 192 }
];

async function createIcon(inputPath, outputPath, size, isRound = false) {
  try {
    let pipeline = sharp(inputPath, { density: 300 });

    if (isRound) {
      // Create circular mask for rounded icon
      const circleSize = size;
      const svgCircle = Buffer.from(
        `<svg><circle cx="${circleSize/2}" cy="${circleSize/2}" r="${circleSize/2}" fill="white"/></svg>`
      );

      pipeline = pipeline
        .resize(size, size, { fit: 'cover', position: 'center' })
        .composite([{ input: svgCircle, blend: 'dest-in' }]);
    } else {
      pipeline = pipeline.resize(size, size, { fit: 'contain', background: { r: 255, g: 255, b: 255, alpha: 0 } });
    }

    await pipeline.png().toFile(outputPath);
    console.log(`Created: ${outputPath}`);
  } catch (error) {
    console.error(`Error creating ${outputPath}:`, error.message);
  }
}

async function convertIcons() {
  // Ensure directories exist
  for (const size of sizes) {
    const dir = path.join(baseResPath, `mipmap-${size.name}`);
    if (!fs.existsSync(dir)) {
      fs.mkdirSync(dir, { recursive: true });
    }
  }

  // Convert ic_launcher.png
  console.log('Converting ic_launcher.png...');
  for (const size of sizes) {
    const outputPath = path.join(baseResPath, `mipmap-${size.name}`, 'ic_launcher.png');
    await createIcon(svgPath, outputPath, size.size, false);
  }

  // Convert ic_launcher_round.png
  console.log('\nConverting ic_launcher_round.png...');
  for (const size of sizes) {
    const outputPath = path.join(baseResPath, `mipmap-${size.name}`, 'ic_launcher_round.png');
    await createIcon(svgPath, outputPath, size.size, true);
  }

  console.log('\nIcon conversion complete!');
}

convertIcons().catch(console.error);
